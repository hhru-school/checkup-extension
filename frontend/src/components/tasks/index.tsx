import { Col, Row, Switch, Space, Table } from "antd";
import type { ColumnsType } from "antd/es/table";
import styles from "./index.module.css";
import { Link } from "react-router-dom";
import { TabHeader } from "../tab-header";
import { Task } from "../../__data__/slices/tasks";
import { useSelector } from "react-redux";
import { getTasks } from "../../__data__/selectors";
import { useTranslation } from "react-i18next";

export const Tasks = () => {
  const { t } = useTranslation();

  const columns: ColumnsType<Task> = [
    {
      title: t("table.column.id"),
      dataIndex: "id",
      key: "id",
    },
    {
      title: t("table.column.title"),
      dataIndex: "title",
      key: "title",
    },
    {
      title: t("table.column.active"),
      dataIndex: "active",
      key: "active",
      render: (_, { active }) => <Switch checked={active} disabled />,
    },
    {
      title: t("table.column.type"),
      dataIndex: "type",
      key: "type",
      render: (_, { type }) => t(`task.type.${type.toLowerCase()}`),
    },
    {
      title: t("table.column.actions"),
      key: "actions",
      render: (_, record) => <Link to="#">{t("link.edit")}</Link>,
    },
  ];

  const tasks = useSelector(getTasks);
  const data = tasks.map((item) => {
    return { ...item, key: item.id };
  });

  return (
    <>
      <Space direction="vertical" size="large" className={styles.space}>
        <TabHeader title={t("tasks.title")} onAdd={() => {}} />
        <Row>
          <Col span={24}>
            <Table columns={columns} dataSource={data} />
          </Col>
        </Row>
      </Space>
    </>
  );
};
