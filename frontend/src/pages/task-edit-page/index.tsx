import { Typography, Row, Col, Collapse, Space, Button } from "antd";
import styles from "./index.module.css";
import { useTranslation } from "react-i18next";
import { useSelector } from "react-redux";
import { StoreType } from "../../__data__/store";
import { Link, useParams } from "react-router-dom";
import { Editor } from "../../components/editor";
import { History } from "../../components/history";
import ReactMarkdown from "react-markdown";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { coy } from "react-syntax-highlighter/dist/esm/styles/prism";
import { LeftOutlined } from "@ant-design/icons";

export const Page = () => {
  const { t } = useTranslation();
  const { id } = useParams();
  const task = useSelector((state: StoreType) =>
    state.tasks.tasks.find((item) => item.id === Number(id))
  );

  const handleHtmlEditorChange = (value: string) => {};
  const handleCssEditorChange = (value: string) => {};
  const handleJsEditorChange = (value: string) => {};

  if (!task) {
    return null;
  }

  return (
    <>
      <Space direction="vertical" size="middle" style={{ display: "flex" }}>
        <Row align="middle">
          <Col span={20}>
            <Typography.Title level={1}>{task.title}</Typography.Title>
          </Col>
          <Col span={4}>
            <Link to={"/"}>
              <Button icon={<LeftOutlined />}>{t("button.back")}</Button>
            </Link>
          </Col>
        </Row>
        <Row>
          <Col span={24}>
            <Collapse defaultActiveKey={["0"]} ghost>
              <Collapse.Panel header={t("tasks.description.title")} key="0">
                <ReactMarkdown
                  children={task.content}
                  components={{
                    code({ node, inline, className, children, ...props }) {
                      const match = /language-(\w+)/.exec(className || "");
                      return !inline && match ? (
                        <SyntaxHighlighter
                          {...props}
                          children={String(children).replace(/\n$/, "")}
                          style={coy}
                          language={match[1]}
                          PreTag="div"
                        />
                      ) : (
                        <code {...props} className={className}>
                          {children}
                        </code>
                      );
                    },
                  }}
                />
              </Collapse.Panel>
            </Collapse>
          </Col>
        </Row>
        <Row gutter={20}>
          <Col span={16}>
            {task.type === "html" && (
              <>
                <Space
                  direction="vertical"
                  size="middle"
                  style={{ display: "flex" }}
                >
                  <Editor
                    mode="html"
                    title="HTML"
                    onChange={handleHtmlEditorChange}
                  />
                  <Editor
                    mode="css"
                    title="CSS"
                    onChange={handleCssEditorChange}
                  />
                </Space>
              </>
            )}
            {task.type === "js" && (
              <>
                <Editor mode="js" title="JS" onChange={handleJsEditorChange} />
              </>
            )}
          </Col>
          <Col span={8}>
            <Space
              direction="vertical"
              size="middle"
              style={{ display: "flex" }}
            >
              <Button
                type="primary"
                block
                size="large"
                style={{ margin: "0px" }}
              >
                {t("button.sent")}
              </Button>
              <History />
            </Space>
          </Col>
        </Row>
      </Space>
    </>
  );
};
