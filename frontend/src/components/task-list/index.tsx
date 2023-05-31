import { FC, useEffect } from "react";
import styles from "./index.module.css";
import { Alert, Avatar, Button, List, Steps } from "antd";
import html from "./html.svg";
import js from "./js.svg";
import { Link } from "react-router-dom";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { getTasks } from "../../__data__/slices/tasks";
import { useTranslation } from "react-i18next";

const stepsItems = [
  {
    title: "Решение",
    description: "Ожидается решение задачи и отправка на проверку",
  },
  {
    title: "Проверка",
    description: "Решение находится на проверке",
  },
  {
    title: "Результат",
    description: "Решение проверено, можно посмотреть результаты",
  },
];

// TODO: show only active task
// TODO: skeleton?
// TODO: 404 and 500 error handle
export const TaskList: FC = () => {
  const { t } = useTranslation();
  const dispatch = useAppDispatch();
  const loading = useAppSelector((store) => store.tasks.isLoading);
  const error = useAppSelector((store) => store.tasks.error);
  const tasks = useAppSelector((store) => store.tasks.tasks);

  useEffect(() => {
    dispatch(getTasks());
  }, [dispatch]);

  if (error) {
    return (
      <Alert
        message={t("tasks.error.title")}
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
    <div>
      <List
        loading={loading}
        itemLayout="horizontal"
        dataSource={tasks}
        bordered
        renderItem={(item, index) => (
          <List.Item>
            <List.Item.Meta
              avatar={
                <Avatar
                  shape="square"
                  size={64}
                  src={item.type === "JS" ? js : html}
                />
              }
              title={<Link to={String(item.id)}>{item.title}</Link>}
              description={item.description}
            />
            <Steps
              className={styles.steps}
              type="inline"
              current={0}
              status="wait"
              items={stepsItems}
            />
          </List.Item>
        )}
      ></List>
    </div>
  );
};
