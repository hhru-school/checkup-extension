import { FC } from "react";
import styles from "./index.module.css";
import { Avatar, List, Steps } from "antd";
import html from "./html.svg";
import js from "./js.svg";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { StoreType } from "../../__data__/store";

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

export const TaskList: FC = () => {
  // TODO: show only active task
  const tasks = useSelector((state: StoreType) => state.tasks.tasks);
  return (
    <div>
      <List
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
