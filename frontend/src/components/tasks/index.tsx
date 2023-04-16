import { Col, Row, Switch, Space, Table } from "antd";
import type { ColumnsType } from "antd/es/table";
import styles from "./index.module.css";
import i18next from "../../i18n";
import { Link } from "react-router-dom";
import { TabHeader } from "../tab-header";
import type { Task } from "../../__data__/slices";
import { useSelector } from "react-redux";
import { getTasks } from "../../__data__/selectors";

export const Tasks = () => {
  const columns: ColumnsType<Task> = [
    {
      title: i18next.t("table.column.id"),
      dataIndex: "id",
      key: "id",
    },
    {
      title: i18next.t("table.column.title"),
      dataIndex: "title",
      key: "title",
    },
    {
      title: i18next.t("table.column.active"),
      dataIndex: "active",
      key: "active",
      render: (_, { active }) => <Switch checked={active} disabled />,
    },
    {
      title: i18next.t("table.column.type"),
      dataIndex: "type",
      key: "type",
      render: (_, { type }) => i18next.t(`task.type.${type}`),
    },
    {
      title: i18next.t("table.column.actions"),
      key: "actions",
      render: (_, record) => <Link to="#">{i18next.t("link.edit")}</Link>,
    },
  ];

  const tasks = useSelector(getTasks);
  const data = tasks.map((item) => {
    return { ...item, key: item.id };
  });

  return (
    <>
      <Space direction="vertical" size="large" className={styles.space}>
        <TabHeader title={i18next.t("tasks.title")} onAdd={() => {}} />
        <Row>
          <Col span={24}>
            <Table columns={columns} dataSource={data} />
          </Col>
        </Row>
      </Space>
    </>
  );
};
