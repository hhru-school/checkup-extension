import { Col, Row, Space, Table, Radio } from "antd";
import type { ColumnsType } from "antd/es/table";
import styles from "./index.module.css";
import { Link } from "react-router-dom";
import { TabHeader } from "../tab-header";
import { useTranslation } from "react-i18next";

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
  const { t } = useTranslation();

  const columns: ColumnsType<DataType> = [
    {
      title: t("table.column.id"),
      dataIndex: "id",
      key: "id",
    },
    {
      title: t("table.column.challenge"),
      dataIndex: "challenge",
      key: "challenge",
    },
    {
      title: t("table.column.user"),
      dataIndex: "user",
      key: "user",
    },
    {
      title: t("table.column.status"),
      dataIndex: "status",
      key: "status",
    },
    {
      title: t("table.column.actions"),
      key: "actions",
      render: (_, record) => <Link to="#">{t("link.preview")}</Link>,
    },
  ];

  return (
    <>
      <Space direction="vertical" size="large" className={styles.space}>
        <TabHeader title={t("results.title")} />
        <Radio.Group defaultValue="challenge">
          <Radio.Button value="challenge">
            {t("table.column.challenge")}
          </Radio.Button>
          <Radio.Button value="status">{t("table.column.status")}</Radio.Button>
          <Radio.Button value="user">{t("table.column.user")}</Radio.Button>
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
