import { ReactNode } from "react";
import styles from "./index.module.css";

type Props = {
  header: ReactNode;
  children: ReactNode;
};

export const CheckupContainer = ({ header, children }: Props) => (
  <div className={styles.root}>
    <div>{header}</div>

    <div className={styles.content}>{children}</div>
  </div>
);
