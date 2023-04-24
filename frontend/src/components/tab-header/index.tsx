import { Typography, Button, Col, Row } from "antd";
import styles from "./index.module.css";
import { PlusOutlined } from "@ant-design/icons";
import { FC } from "react";
import i18next from "../../i18n";

type PropsType = {
  title: string;
  onAdd?: () => void;
};

export const TabHeader: FC<PropsType> = ({ title, onAdd }) => {
  return (
    <>
      <Row align="middle">
        <Col span={12}>
          <Typography.Title level={2}>{title}</Typography.Title>
        </Col>
        <Col span={12} className={styles.col}>
          {onAdd && (
            <Button onClick={onAdd} icon={<PlusOutlined />}>
              {i18next.t("button.add")}
            </Button>
          )}
        </Col>
      </Row>
    </>
  );
};
