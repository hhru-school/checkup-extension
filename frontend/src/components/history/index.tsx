import { FC, useState } from "react";
import styles from "./index.module.css";
import { Button, List, Typography } from "antd";
import { useTranslation } from "react-i18next";

const data = [
  {
    id: 5,
    title: "Текущее решение",
    date: "2023-04-09 15:22",
  },
  {
    id: 4,
    title: "Попытка №5",
    date: "2023-04-09 15:22",
  },
  {
    id: 3,
    title: "Попытка №4",
    date: "2023-04-09 15:22",
  },
  {
    id: 2,
    title: "Попытка №3",
    date: "2023-04-09 15:22",
  },
  {
    id: 1,
    title: "Попытка №2",
    date: "2023-04-09 15:22",
  },
  {
    id: 0,
    title: "Попытка №1",
    date: "2023-04-09 15:22",
  },
];

export const History: FC = () => {
  const { t } = useTranslation();

  const handleShowButtonClick = (id: number) => {
    // TODO: update store
  };

  return (
    <>
      <List
        header={
          <Typography.Title level={3}>{t("history.title")}</Typography.Title>
        }
        size="small"
        bordered
        dataSource={data}
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
