import { Alert, Button } from "antd";
import { FC } from "react";
import { useTranslation } from "react-i18next";

type PropsType = {
  mode: "info" | "warning" | "error";
};

export const Banner: FC<PropsType> = ({ mode }) => {
  const { t } = useTranslation();
  return (
    <Alert
      message={t("tasks.error.title")}
      description={t("error.loading")}
      type={mode}
      action={
        <Button
          size="small"
          danger
          onClick={() => {
            window.location.reload();
          }}
        >
          {t("button.reload")}
        </Button>
      }
    />
  );
};
