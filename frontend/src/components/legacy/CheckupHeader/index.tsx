import styles from "./index.module.css";
import { CheckupMenu } from "../CheckupMenu";
import { CheckupInfo } from "../CheckupInfo";
import { CheckupLogo } from "../CheckupLogo";

export const CheckupHeader = () => {
  return (
    <div className={styles.root}>
      <div className={styles.container}>
        <CheckupLogo />

        <CheckupMenu />

        <CheckupInfo />
      </div>
    </div>
  );
};
