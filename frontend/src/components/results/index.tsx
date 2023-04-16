import { Col, Row, Space, Table, Radio } from "antd";
import type { ColumnsType } from "antd/es/table";
import styles from "./index.module.css";
import i18next from "../../i18n";
import { Link } from "react-router-dom";
import { TabHeader } from "../tab-header";

type DataType = {
  id: number;
  key: string;
  challenge: string;
  user: string;
  status: string;
};

const data: Array<DataType> = [
  {
    id: 1,
    key: "1",
    challenge: "Испытание школы 2022",
    user: "Иванов Иван",
    status: "Пройдено",
  },
  {
    id: 2,
    key: "2",
    challenge: "Испытание школы 2021",
    user: "Петров Петр",
    status: "В процессе",
  },
];

export const Results = () => {
  const columns: ColumnsType<DataType> = [
    {
      title: i18next.t("table.column.id"),
      dataIndex: "id",
      key: "id",
    },
    {
      title: i18next.t("table.column.challenge"),
      dataIndex: "challenge",
      key: "challenge",
    },
    {
      title: i18next.t("table.column.user"),
      dataIndex: "user",
      key: "user",
    },
    {
      title: i18next.t("table.column.status"),
      dataIndex: "status",
      key: "status",
    },
    {
      title: i18next.t("table.column.actions"),
      key: "actions",
      render: (_, record) => <Link to="#">{i18next.t("link.preview")}</Link>,
    },
  ];

  return (
    <>
      <Space direction="vertical" size="large" className={styles.space}>
        <TabHeader title={i18next.t("results.title")} />
        <Radio.Group defaultValue="challenge">
          <Radio.Button value="challenge">
            {i18next.t("table.column.challenge")}
          </Radio.Button>
          <Radio.Button value="status">
            {i18next.t("table.column.status")}
          </Radio.Button>
          <Radio.Button value="user">
            {i18next.t("table.column.user")}
          </Radio.Button>
        </Radio.Group>
        <Row>
          <Col span={24}>
            <Table columns={columns} dataSource={data} />
          </Col>
        </Row>
      </Space>
    </>
  );
};
