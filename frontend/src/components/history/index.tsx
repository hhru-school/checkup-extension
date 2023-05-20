import { FC, useEffect, useState } from "react";
import styles from "./index.module.css";
import { Button, Col, List, Row, Skeleton, Space, Typography } from "antd";
import { useTranslation } from "react-i18next";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { fetchHistory } from "../../__data__/slices/history";
import { useParams } from "react-router-dom";

export const History: FC = () => {
  const { t } = useTranslation();
  const { id } = useParams();
  const dispatch = useAppDispatch();
  const loading = useAppSelector((store) => store.history.isLoading);
  const error = useAppSelector((store) => store.history.error);
  const solutions = useAppSelector((store) => store.history.solutions);

  useEffect(() => {
    dispatch(fetchHistory(Number(id)));
  }, [dispatch, id]);

  const handleShowButtonClick = (id: number) => {
    // TODO: update store
  };

  if (true) {
    return (
      <>
        <Row gutter={8}>
          <Col span={18}>
            <Skeleton.Input active size="large" block={true} />
          </Col>
          <Col span={4}>
            <Skeleton.Button
              active
              size="large"
              shape="default"
              block={false}
            />
          </Col>
        </Row>
      </>
    );
  }

  if (error) {
    return null;
  }

  return (
    <>
      <List
        header={
          <Typography.Title level={3}>{t("history.title")}</Typography.Title>
        }
        size="small"
        bordered
        dataSource={solutions}
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
