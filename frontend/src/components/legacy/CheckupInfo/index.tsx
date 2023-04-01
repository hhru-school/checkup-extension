import styles from "./index.module.css";

export const CheckupInfo = () => {
  return (
    <div className={styles.root}>
      <div>Иван Иванов</div>

      <button className={styles.button} type="button">
        Выход
      </button>
    </div>
  );
};
