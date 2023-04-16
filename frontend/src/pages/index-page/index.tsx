import { Typography, Switch, Divider } from "antd";
import styles from "./index.module.css";
import { TaskList } from "../../components/task-list";
import { useTranslation } from "react-i18next";
import { Admin } from "./admin";
import { useState } from "react";

export const Page = () => {
  const { t } = useTranslation();
  const [mode, setMode] = useState<"admin" | "user">("admin");
  const handleChange = (checked: boolean) => {
    if (checked) {
      setMode("admin");
    } else {
      setMode("user");
    }
  };
  return (
    <>
      <Switch
        checkedChildren="admin"
        unCheckedChildren="user"
        defaultChecked
        onChange={handleChange}
      />
      <Divider />
      {mode === "admin" && <Admin />}
      {mode === "user" && (
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
      )}
    </>
  );
};
