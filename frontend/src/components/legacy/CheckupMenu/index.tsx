import styles from "./index.module.css";

export const CheckupMenu = () => {
  return (
    <ul className={styles.root}>
      <li>
        <a className={styles.link} href="#tasks">
          Задания
        </a>
      </li>
      <li>
        <a className={styles.link} href="#extension">
          Extension
        </a>
      </li>
    </ul>
  );
};
