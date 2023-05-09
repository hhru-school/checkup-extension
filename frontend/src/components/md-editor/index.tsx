import { FC } from "react";
import styles from "./index.module.css";
import MdEditor from "@uiw/react-markdown-editor";

type PropsType = {
  value: string;
  onChange: (value: string) => void;
};

export const MarkdownEditor: FC<PropsType> = ({ value, onChange }) => {
  return (
    <>
      <MdEditor
        value={value}
        onChange={(value, viewUpdate) => onChange(value)}
        className={styles.editor}
      />
    </>
  );
};
