import { Col, Row, Switch, Space, Table } from "antd";
import type { ColumnsType, TablePaginationConfig } from "antd/es/table";
import styles from "./index.module.css";
import { Link, useNavigate } from "react-router-dom";
import { TabHeader } from "../tab-header";
import { getTasksToEdit } from "../../__data__/slices/tasks";
import { useTranslation } from "react-i18next";
import { TaskShort } from "../../types";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { useEffect, useState } from "react";
import { Banner } from "../banner";

export const TaskTable = () => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const [data, setData] = useState<Array<TaskShort>>();
  const [pagination, setPagination] = useState<TablePaginationConfig>({
    current: 1,
    pageSize: 10,
  });
  const dispatch = useAppDispatch();
  const loading = useAppSelector((store) => store.tasks.isLoading);
  const error = useAppSelector((store) => store.tasks.error);
  const tasks = useAppSelector((store) => store.tasks.tasksToEdit);

  useEffect(() => {
    dispatch(
      getTasksToEdit({
        page: pagination.current as number,
        size: pagination.pageSize as number,
      })
    );
  }, [dispatch, JSON.stringify(pagination)]);

  useEffect(() => {
    if (tasks) {
      setData(
        tasks.records.map((item) => {
          return { ...item, key: item.id };
        })
      );

      setPagination({
        ...pagination,
        total: tasks.total,
      });
    }
  }, [JSON.stringify(pagination), tasks]);

  const handleTableChange = (pagination: TablePaginationConfig) => {
    setPagination({
      ...pagination,
    });
  };

  const columns: ColumnsType<TaskShort> = [
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
      render: (_, record) => (
        <Link to={`new-task/${record.id}`}>{t("link.edit")}</Link>
      ),
    },
  ];

  if (error) {
    return <Banner mode="error" />;
  }

  return (
    <>
      <Space direction="vertical" size="large" className={styles.space}>
        <TabHeader
          title={t("tasks.title")}
          onAdd={() => {
            navigate("new-task");
          }}
        />
        <Row>
          <Col span={24}>
            <Table
              columns={columns}
              dataSource={data}
              loading={loading}
              pagination={pagination}
              onChange={handleTableChange}
            />
          </Col>
        </Row>
      </Space>
    </>
  );
};
