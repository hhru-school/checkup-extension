import { useTranslation } from "react-i18next";
import styles from "./index.module.css";

export const CheckupMenu = () => {
  const { t } = useTranslation();

  return (
    <ul className={styles.root}>
      <li>
        <a className={styles.link} href="/admin">
          {t("menu.admin")}
        </a>
      </li>
    </ul>
  );
};
