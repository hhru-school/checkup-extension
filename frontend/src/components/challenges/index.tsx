import { Typography, Col, Row, Switch, Space, Table } from "antd";
import type { ColumnsType } from "antd/es/table";
import styles from "./index.module.css";
import i18next from "../../i18n";
import { Link } from "react-router-dom";
import { TabHeader } from "../tab-header";

type DataType = {
  id: number;
  key: string;
  title: string;
  active: boolean;
  comment: string;
};

const data: Array<DataType> = [
  {
    id: 1,
    key: "1",
    title: "Испытание школы 2022",
    active: true,
    comment: "Проводится - этап 'проекты'",
  },
  {
    id: 2,
    key: "2",
    title: "Испытание школы 2021",
    active: false,
    comment: "Завершен",
  },
];

export const Challenges = () => {
  const columns: ColumnsType<DataType> = [
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
      align: "center",
      render: (_, { active }) => <Switch checked={active} disabled />,
    },
    {
      title: i18next.t("table.column.comment"),
      dataIndex: "comment",
      key: "comment",
      render: (_, { comment }) => (
        <Typography.Paragraph
          ellipsis={{ tooltip: comment }}
          className={styles.comment}
        >
          {comment}
        </Typography.Paragraph>
      ),
    },
    {
      title: i18next.t("table.column.actions"),
      key: "actions",
      render: (_, record) => (
        <Space size="middle">
          <Link to="#">{i18next.t("link.preview")}</Link>
          <Link to="#">{i18next.t("link.edit")}</Link>
        </Space>
      ),
    },
  ];

  return (
    <>
      <Space direction="vertical" size="large" className={styles.space}>
        <TabHeader title={i18next.t("challenges.title")} onAdd={() => {}} />
        <Row>
          <Col span={24}>
            <Table columns={columns} dataSource={data} />
          </Col>
        </Row>
      </Space>
    </>
  );
};
