import { FC, useEffect, useRef, useState } from "react";
import styles from "./index.module.css";
import {
  Alert,
  Button,
  Col,
  List,
  Row,
  Skeleton,
  Space,
  Typography,
  Tag,
} from "antd";
import { useTranslation } from "react-i18next";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { getHistory, actions } from "../../__data__/slices/history";
import { SolutionShort, StatusTypes } from "../../types";
import { SyncOutlined } from "@ant-design/icons";

const HistorySkeleton = () => {
  const rows = new Array(7).fill(null).map((item, index) => {
    return (
      <Row gutter={8} key={index}>
        <Col span={18}>
          <Skeleton.Input active size="large" block={true} />
        </Col>
        <Col span={4}>
          <Skeleton.Button active size="large" shape="default" block={false} />
        </Col>
      </Row>
    );
  });

  return (
    <>
      <Space direction="vertical" className={styles.space}>
        {rows}
      </Space>
    </>
  );
};

enum TagColors {
  checked = "geekblue",
  success = "green",
  fault = "red",
}

type PropsType = {
  taskId: number;
};

export const History: FC<PropsType> = ({ taskId }) => {
  const { t } = useTranslation();
  const [solutionsCopy, setSolutionsCopy] = useState<Array<SolutionShort>>([]);
  const dispatch = useAppDispatch();
  const loading = useAppSelector((store) => store.history.isLoading);
  const error = useAppSelector((store) => store.history.error);
  const solutions = useAppSelector((store) => store.history.solutions);
  const timerId = useRef<NodeJS.Timeout | null>();
  const polling = useRef<boolean>(false);

  useEffect(() => {
    dispatch(getHistory(taskId));
  }, [dispatch, taskId]);

  useEffect(() => {
    if (solutions) {
      const solutionsWithTitle = solutions.map((item, index) => ({
        ...item,
        title: t("history.solution.title", { index: index + 1 }),
      }));
      setSolutionsCopy([
        {
          submissionId: -1,
          date: new Date().toLocaleString(),
          status: "inprogress",
          title: t("history.solution.current.title"),
        },
        ...solutionsWithTitle,
      ]);
    }
  }, [solutions, t]);

  useEffect(() => {
    if (solutions) {
      const isChecked = solutions.some((item) => item.status === "checked");
      if (isChecked && !timerId.current) {
        polling.current = true;
        timerId.current = setTimeout(() => {
          dispatch(getHistory(taskId));
          timerId.current = null;
        }, 5000);
      }
    }
  }, [dispatch, solutions, taskId]);

  const handleShowButtonClick = (id: number) => {
    dispatch(actions.setCurrentSolution(id));
  };

  if (loading && !polling) {
    return <HistorySkeleton />;
  }

  if (error) {
    return (
      <Alert
        message={t("history.error.title")}
        description={t("error.loading")}
        type="error"
        action={
          <Button
            size="small"
            danger
            onClick={() => {
              window.location.reload();
            }}
          >
            {t("button.reload")}
          </Button>
        }
      />
    );
  }

  return (
    <>
      <List
        header={
          <Typography.Title level={3}>{t("history.title")}</Typography.Title>
        }
        size="small"
        bordered
        loading={loading && !polling}
        dataSource={solutionsCopy}
        locale={{ emptyText: t("history.no.data") }}
        rowKey={(item) => item.submissionId}
        renderItem={(item) => (
          <List.Item
            className={styles.item}
            onClick={() => handleShowButtonClick(item.submissionId)}
            actions={[
              <Tag
                icon={item.status === "checked" && <SyncOutlined spin />}
                color={TagColors[item.status as StatusTypes]}
              >
                {t("status.text", { context: item.status })}
              </Tag>,
            ]}
          >
            <List.Item.Meta title={item.title} description={item.date} />
          </List.Item>
        )}
      />
    </>
  );
};
