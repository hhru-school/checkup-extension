import {
  Typography,
  Row,
  Col,
  Collapse,
  Space,
  Button,
  Alert,
  message,
} from "antd";
import styles from "./index.module.css";
import { useTranslation } from "react-i18next";
import {
  StoreType,
  useAppDispatch,
  useAppSelector,
} from "../../__data__/store";
import { Link, useParams } from "react-router-dom";
import { History } from "../../components/history";
import ReactMarkdown from "react-markdown";
import { Prism as SyntaxHighlighter } from "react-syntax-highlighter";
import { coy } from "react-syntax-highlighter/dist/esm/styles/prism";
import { LeftOutlined } from "@ant-design/icons";
import { CodeEditor } from "../../components/code-editor";
import { FC, useEffect, useState } from "react";
import { getSolution, sendSolution } from "../../__data__/slices/solution";
import { SolutionToSend } from "../../types";

// TODO: выбор решения из истории
// TODO: в список решений добавить текущее решение, не отправленное
// TODO: текущее, не отправленное решение, сохранять в LS
// TODO:
export const Page: FC = () => {
  const [htmlContent, setHtmlContent] = useState("");
  const [cssContent, setCssContent] = useState("");
  const [jsContent, setJsContent] = useState("");
  const { t } = useTranslation();
  const { id: taskId } = useParams();

  const dispatch = useAppDispatch();

  const loading = useAppSelector((store) => store.solution.isLoading);
  const error = useAppSelector((store) => store.solution.error);
  const result = useAppSelector((store) => store.solution.result);
  const currentSolutionId = useAppSelector(
    (store) => store.history.currentSolutionId
  );
  const solution = useAppSelector((store) => store.solution.solution);
  const task = useAppSelector((store: StoreType) =>
    store.tasks.tasks.find((item) => item.id === Number(taskId))
  );

  useEffect(() => {
    if (currentSolutionId > 0) {
      dispatch(getSolution(currentSolutionId));
    }
  }, [currentSolutionId, dispatch]);

  useEffect(() => {
    if (error) {
      window.scroll({ top: 0, behavior: "smooth" });
    }
  }, [error]);

  useEffect(() => {
    if (solution) {
      setHtmlContent(solution.htmlContent);
      setCssContent(solution.cssContent);
      setJsContent(solution.jsContent);
    }
  }, [solution]);

  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    if (loading) {
      messageApi.open({
        type: "loading",
        content: t("message.loading"),
        duration: 0,
      });
    } else {
      messageApi.destroy();
    }
  }, [loading, messageApi, t]);

  const handleSendButtonClick = () => {
    const solution: SolutionToSend = {
      problemId: Number(taskId),
      htmlContent,
      cssContent,
      jsContent,
    };
    dispatch(sendSolution({ solution, taskId: Number(taskId) }));
  };

  if (!task) {
    return null;
  }

  return (
    <>
      {contextHolder}
      <Space direction="vertical" size="middle" style={{ display: "flex" }}>
        {error && (
          <Alert message="Error" description={result} type="error" closable />
        )}
        <Row align="middle">
          <Col span={20}>
            <Typography.Title level={1}>{task.title}</Typography.Title>
          </Col>
          <Col span={4} className={styles.col}>
            <Link to={"/"}>
              <Button disabled={loading} icon={<LeftOutlined />}>
                {t("button.back")}
              </Button>
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
            <Space
              direction="vertical"
              size="middle"
              style={{ display: "flex" }}
            >
              {task.type === "HTML" && (
                <>
                  <Space
                    direction="vertical"
                    size="middle"
                    style={{ display: "flex" }}
                  >
                    <CodeEditor
                      disabled={currentSolutionId > 0}
                      value={htmlContent}
                      mode="solution"
                      language="HTML"
                      onChange={setHtmlContent}
                    />
                    <CodeEditor
                      disabled={currentSolutionId > 0}
                      value={cssContent}
                      mode="solution"
                      language="CSS"
                      onChange={setCssContent}
                    />
                  </Space>
                </>
              )}
              {task.type === "JS" && (
                <>
                  <CodeEditor
                    disabled={currentSolutionId > 0}
                    value={jsContent}
                    mode="solution"
                    language="JavaScript"
                    onChange={setJsContent}
                  />
                </>
              )}
              <Button
                type="primary"
                disabled={currentSolutionId > 0}
                onClick={handleSendButtonClick}
                block
                loading={loading}
                size="large"
                style={{ margin: "0px" }}
              >
                {t("button.send")}
              </Button>
            </Space>
          </Col>
          <Col span={8}>
            <History task={Number(taskId)} />
          </Col>
        </Row>
      </Space>
    </>
  );
};
