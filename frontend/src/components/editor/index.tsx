import { FC, useState } from "react";
import styles from "./index.module.css";
import { Input, Typography, Card } from "antd";

type PropsType = {
  title: string;
  mode: "html" | "js" | "css";
  onChange: (value: string) => void;
};

const initHtmlContent = `<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
</head>
<body>
<!-- Ваш код здесь -->
</body>
</html>`;

export const Editor: FC<PropsType> = ({ title, mode, onChange }) => {
  const [value, setValue] = useState(mode === "html" ? initHtmlContent : "");

  const handleChangeValue = (e: React.ChangeEvent<HTMLTextAreaElement>) => {
    const { target } = e;
    setValue(target.value);
  };

  return (
    <>
      <Card
        title={title}
        headStyle={{ backgroundColor: "#e6f7ff" }}
        bodyStyle={{ padding: "0px" }}
      >
        <Input.TextArea
          bordered={false}
          allowClear
          rows={16}
          className={styles.editor}
          value={value}
          onChange={handleChangeValue}
        />
      </Card>
    </>
  );
};
