import { FC, useEffect, useState } from "react";
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
} from "antd";
import { useTranslation } from "react-i18next";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { fetchHistory, actions } from "../../__data__/slices/history";
import { SolutionShort } from "../../types";

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

type PropsType = {
  task: number;
};
// TODO: переделать дизайн, убрать кнопку выбрать и каждый item сделать активным
export const History: FC<PropsType> = ({ task }) => {
  const { t } = useTranslation();
  const [solutionsCopy, setSolutionsCopy] = useState<Array<SolutionShort>>([]);
  const dispatch = useAppDispatch();
  const loading = useAppSelector((store) => store.history.isLoading);
  const error = useAppSelector((store) => store.history.error);
  const solutions = useAppSelector((store) => store.history.solutions);

  useEffect(() => {
    dispatch(fetchHistory(task));
  }, [dispatch, task]);

  useEffect(() => {
    if (solutions) {
      setSolutionsCopy([
        {
          id: -1,
          date: new Date().toLocaleString(),
          status: "check",
          title: t("history.solution.current.title"),
        },
        ...solutions,
      ]);
    }
  }, [solutions, t]);

  const handleShowButtonClick = (id: number) => {
    dispatch(actions.setCurrentSolution(id));
  };

  if (loading) {
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
        loading={loading}
        dataSource={solutionsCopy}
        locale={{ emptyText: t("history.no.data") }}
        rowKey={(item) => item.id}
        renderItem={(item) => (
          <List.Item
            className={styles.item}
            actions={[
              <Button
                block
                type="dashed"
                onClick={() => handleShowButtonClick(item.id)}
              >
                {t("button.show")}
              </Button>,
            ]}
          >
            <List.Item.Meta title={item.title} description={item.date} />
          </List.Item>
        )}
      />
    </>
  );
};
