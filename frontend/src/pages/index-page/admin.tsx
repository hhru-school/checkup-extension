import { Tabs } from "antd";
import { Challenges } from "../../components/challenges";
import { Tasks } from "../../components/tasks";
import { Results } from "../../components/results";
import i18next from "../../i18n";
import {
  AppstoreOutlined,
  FileOutlined,
  UserOutlined,
} from "@ant-design/icons";

export const Admin = () => {
  return (
    <>
      <Tabs
        defaultActiveKey="1"
        tabPosition="left"
        items={[
          {
            label: (
              <span>
                <AppstoreOutlined />
                {i18next.t("challenges.title")}
              </span>
            ),
            key: "1",
            disabled: false,
            children: <Challenges />,
          },
          {
            label: (
              <span>
                <FileOutlined />
                {i18next.t("tasks.title")}
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
                {i18next.t("results.title")}
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
