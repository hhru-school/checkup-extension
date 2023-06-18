import { useTranslation } from "react-i18next";
import styles from "./index.module.css";
import { Link } from "react-router-dom";

export const CheckupMenu = () => {
  const { t } = useTranslation();

  return (
    <ul className={styles.root}>
      <li>
        <Link className={styles.link} to={"/admin"}>
          {t("menu.admin")}
        </Link>
      </li>
      <li>
        <Link className={styles.link} to={"/"}>
          {t("tasks.title")}
        </Link>
      </li>
    </ul>
  );
};
