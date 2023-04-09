import { Button, List, Typography } from "antd";
import styles from "./index.module.css";
import { TaskList } from "../../components/TaskList";
import { useTranslation } from "react-i18next";

export const Page = () => {
  const { t } = useTranslation();
  return (
    <>
      <Typography.Title className={styles.title} level={1}>
        Проверка знаний фронтендера от школы программистов HH
      </Typography.Title>

      <Typography.Paragraph className={styles.intro}>
        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Adipisci,
        aperiam architecto cupiditate eum excepturi fuga laborum nihil omnis
        perferendis perspiciatis quam temporibus totam voluptas? A aliquam
        impedit incidunt placeat ratione!
      </Typography.Paragraph>

      <Typography.Title level={2}>{t("tasks.title")}</Typography.Title>

      <TaskList />
    </>
  );
};
