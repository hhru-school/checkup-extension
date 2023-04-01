import { Button, List, Typography } from "antd";
import styles from "./index.module.css";

const data = [
  {
    title: "Задача 1",
    subtitle: "Описание задачи 1. Задача на верстку",
  },
  {
    title: "Задача 2",
    subtitle: "Описание задачи 2",
  },
  {
    title: "Задача 3",
    subtitle: "Описание задачи 3",
  },
  {
    title: "Задача 4",
    subtitle: "Описание задачи 4",
  },
];

export const Page = () => (
  <>
    <Typography.Title className={styles.title} level={1}>
      Проверка знаний фронтендера от школы программистов HH
    </Typography.Title>

    <Typography.Paragraph className={styles.intro}>
      Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci,
      aperiam architecto cupiditate eum excepturi fuga laborum nihil omnis
      perferendis perspiciatis quam temporibus totam voluptas? A aliquam impedit
      incidunt placeat ratione!
    </Typography.Paragraph>

    <List
      className={styles.list}
      itemLayout="horizontal"
      dataSource={data}
      renderItem={({ title, subtitle }) => (
        <List.Item
          actions={[
            <Button type="link" key="navigate">
              перейти
            </Button>,
          ]}
        >
          <List.Item.Meta
            title={<a href="https://ant.design">{title}</a>}
            description={subtitle}
          />
        </List.Item>
      )}
    />
  </>
);
