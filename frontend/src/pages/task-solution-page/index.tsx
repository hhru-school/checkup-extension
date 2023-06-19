import {
  Typography,
  Row,
  Col,
  Collapse,
  Space,
  Button,
  Alert,
  message,
  Skeleton,
} from "antd";
import styles from "./index.module.css";
import { useTranslation } from "react-i18next";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
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
import { getTask } from "../../__data__/slices/tasks";
import { MIN_CONTENT_LENGTH } from "../../__data__/constants/constants";
import { getFromLocalStorage, setToLocalStorage } from "../../utils";
import { getHistory } from "../../__data__/slices/history";

const SolutionSkeleton = () => {
  return (
    <>
      <Row>
        <Col span={14}>
          <Skeleton.Input active block={true} />
          <br />
          <br />
          <br />
        </Col>
        <Col span={24}>
          <Skeleton active />
          <br />
          <br />
          <br />
        </Col>
      </Row>
      <Row>
        <Col span={14}>
          <Skeleton paragraph={{ rows: 7 }} active />
          <Skeleton.Button active block={true} />
        </Col>
      </Row>
    </>
  );
};

// TODO: ошибка - выводим сообщение об ошибке
// TODO: 2 раза срабатывает запрос данных, разобраться почему, возможно роутинг
// TODO: при загрузке задачи проверяем LS, если там что то есть грузим его, или предлагаем загрузить

export const Page: FC = () => {
  const [htmlContent, setHtmlContent] = useState("");
  const [cssContent, setCssContent] = useState("");
  const [jsContent, setJsContent] = useState("");
  const [warning, setWarning] = useState("");
  const { t } = useTranslation();
  const { id: taskId } = useParams();

  const dispatch = useAppDispatch();

  const loadingSolution = useAppSelector((store) => store.solution.isLoading);
  const errorSolution = useAppSelector((store) => store.solution.error);
  const resultSolution = useAppSelector((store) => store.solution.result);
  const currentSolutionId = useAppSelector(
    (store) => store.history.currentSolutionId
  );
  const solution = useAppSelector((store) => store.solution.solution);
  const loadingTask = useAppSelector((store) => store.tasks.isLoading);
  const errorTask = useAppSelector((store) => store.tasks.error);
  const task = useAppSelector((store) => store.tasks.task);

  useEffect(() => {
    dispatch(getTask(Number(taskId)));
  }, [dispatch, taskId]);

  useEffect(() => {
    if (task) {
      const html = getFromLocalStorage<string>("html");
      const css = getFromLocalStorage<string>("css");
      const js = getFromLocalStorage<string>("js");
      setHtmlContent(html ?? task.htmlTemplate);
      setCssContent(css ?? task.cssTemplate);
      setJsContent(js ?? task.jsTemplate);
    }
  }, [task]);

  useEffect(() => {
    if (resultSolution) {
      dispatch(getHistory({ problemId: Number(taskId) }));
    }
  }, [dispatch, resultSolution, taskId]);

  useEffect(() => {
    if (currentSolutionId > 0) {
      dispatch(getSolution(currentSolutionId));
    } else {
      const html = getFromLocalStorage<string>("html");
      const css = getFromLocalStorage<string>("css");
      const js = getFromLocalStorage<string>("js");
      setHtmlContent(html ?? "");
      setCssContent(css ?? "");
      setJsContent(js ?? "");
    }
  }, [currentSolutionId, dispatch]);

  useEffect(() => {
    if (errorSolution || errorTask) {
      window.scroll({ top: 0, behavior: "smooth" });
    }
  }, [errorSolution, errorTask]);

  useEffect(() => {
    if (solution) {
      setHtmlContent(solution.htmlPart);
      setCssContent(solution.cssPart);
      setJsContent(solution.jsPart);
    }
  }, [solution]);

  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    if (loadingSolution) {
      messageApi.open({
        type: "loading",
        content: t("message.loading"),
        duration: 0,
      });
    } else {
      messageApi.destroy();
    }
  }, [loadingSolution, loadingTask, messageApi, t]);

  const handleHtmlContentChange = (value: string) => {
    setHtmlContent(value);
    setToLocalStorage("html", value);
  };

  const handleCssContentChange = (value: string) => {
    setCssContent(value);
    setToLocalStorage("css", value);
  };

  const handleJsContentChange = (value: string) => {
    setJsContent(value);
    setToLocalStorage("js", value);
  };

  const handleSendButtonClick = () => {
    if (
      htmlContent.length > MIN_CONTENT_LENGTH ||
      cssContent.length > MIN_CONTENT_LENGTH ||
      jsContent.length > MIN_CONTENT_LENGTH
    ) {
      const solution: SolutionToSend = {
        problemId: Number(taskId),
        htmlPart: htmlContent,
        cssPart: cssContent,
        jsPart: jsContent,
      };
      setWarning("");
      setToLocalStorage("html", "");
      setToLocalStorage("css", "");
      setToLocalStorage("js", "");
      setHtmlContent("");
      setCssContent("");
      setJsContent("");
      dispatch(sendSolution({ solution, taskId: Number(taskId) }));
    } else {
      setWarning(t("message.not.content") as string);
      window.scroll({ top: 0, behavior: "smooth" });
    }
  };

  if (loadingTask) {
    return <SolutionSkeleton />;
  }

  if (!task) {
    return null;
  }

  return (
    <>
      {contextHolder}
      <Space direction="vertical" size="middle" style={{ display: "flex" }}>
        {errorSolution && (
          <Alert
            message={t("message.error.title")}
            description={errorSolution}
            type="error"
            closable
          />
        )}
        {errorTask && (
          <Alert
            message={t("message.error.title")}
            description={errorTask}
            type="error"
            closable
          />
        )}
        {resultSolution && (
          <Alert
            message={t("message.success.title")}
            description={t("message.solution.add")}
            type="success"
            closable
          />
        )}
        {warning && (
          <Alert
            message={t("code.editor.title")}
            description={warning}
            type="warning"
            closable
            onClose={() => {
              setWarning("");
            }}
          />
        )}
        <Row align="middle">
          <Col span={20}>
            <Typography.Title level={1}>{task.title}</Typography.Title>
          </Col>
          <Col span={4} className={styles.col}>
            <Link to={"/"}>
              <Button
                disabled={loadingSolution || loadingTask}
                icon={<LeftOutlined />}
              >
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
              {task.type === "html" && (
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
                      onChange={handleHtmlContentChange}
                    />
                    <CodeEditor
                      disabled={currentSolutionId > 0}
                      value={cssContent}
                      mode="solution"
                      language="CSS"
                      onChange={handleCssContentChange}
                    />
                  </Space>
                </>
              )}
              {task.type === "js" && (
                <>
                  <CodeEditor
                    disabled={currentSolutionId !== -1}
                    value={jsContent}
                    mode="solution"
                    language="JavaScript"
                    onChange={handleJsContentChange}
                  />
                </>
              )}
              <Button
                type="primary"
                disabled={currentSolutionId !== -1}
                onClick={handleSendButtonClick}
                block
                loading={loadingSolution}
                size="large"
                style={{ margin: "0px" }}
              >
                {t("button.send")}
              </Button>
            </Space>
          </Col>
          <Col span={8}>
            <History taskId={Number(taskId)} />
          </Col>
        </Row>
      </Space>
    </>
  );
};
