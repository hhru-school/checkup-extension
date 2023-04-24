import { Tabs } from "antd";
import { Tasks } from "../../components/tasks";
import { Results } from "../../components/results";
import { FileOutlined, UserOutlined } from "@ant-design/icons";
import { useTranslation } from "react-i18next";

export const Admin = () => {
  const { t } = useTranslation();

  return (
    <>
      <Tabs
        defaultActiveKey="1"
        tabPosition="left"
        items={[
          {
            label: (
              <span>
                <FileOutlined />
                {t("tasks.title")}
              </span>
            ),
            key: "2",
            disabled: false,
            children: <Tasks />,
          },
          {
            label: (
              <span>
                <UserOutlined />
                {t("results.title")}
              </span>
            ),
            key: "3",
            disabled: false,
            children: <Results />,
          },
        ]}
      />
    </>
  );
};
