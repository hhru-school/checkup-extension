import { FC } from "react";
import styles from "./index.module.css";
import ReactCodeEditor from "@uiw/react-textarea-code-editor";
import { useTranslation } from "react-i18next";
import { Card } from "antd";

type PropsType = {
  value: string;
  language: "JavaScript" | "CSS" | "HTML";
  mode: "template" | "solution" | "test";
  onChange: (value: string, index?: number) => void;
  index?: number;
  disabled?: boolean;
};

export const CodeEditor: FC<PropsType> = ({
  value,
  language,
  onChange,
  mode,
  index = 0,
  disabled = false,
}) => {
  const { t } = useTranslation();

  const handleChange = (event: React.ChangeEvent<HTMLTextAreaElement>) => {
    onChange(event.target.value, index);
  };
  // TODO: add delete button
  return (
    <>
      <Card
        title={t("code.editor.title", {
          context: mode,
          language,
          index: index + 1,
        })}
        bodyStyle={{ padding: 0 }}
      >
        <div className={styles.wrapper}>
          <ReactCodeEditor
            disabled={disabled}
            value={value}
            language={language.toLowerCase()}
            placeholder={
              t("code.editor.placeholder", {
                context: mode,
                language,
              }) as string
            }
            onChange={handleChange}
            className={styles.editor}
            style={{
              fontSize: "1rem",
            }}
            minHeight={200}
          />
        </div>
      </Card>
    </>
  );
};
