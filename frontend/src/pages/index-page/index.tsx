import { Typography } from "antd";
import styles from "./index.module.css";
import { TaskList } from "../../components/task-list";
import { useTranslation } from "react-i18next";

export const Page = () => {
  const { t } = useTranslation();

  return (
    <>
      <Typography.Title className={styles.title} level={1}>
        {t("main.title")}
      </Typography.Title>

      <Typography.Paragraph className={styles.intro}>
        {t("main.description")}
      </Typography.Paragraph>

      <Typography.Title level={2}>{t("tasks.title")}</Typography.Title>

      <TaskList />
    </>
  );
};
