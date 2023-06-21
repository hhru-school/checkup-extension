import {
  Typography,
  Col,
  Row,
  Collapse,
  Input,
  Select,
  Switch,
  Alert,
  Tooltip,
  Button,
  message,
  Space,
  Skeleton,
} from "antd";
import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import styles from "./index.module.css";
import { useTranslation } from "react-i18next";
import ReactMarkdown from "react-markdown";
import { MarkdownEditor } from "../../components/md-editor";
import { CodeEditor } from "../../components/code-editor";
import { FC, useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { addNewTasks, updateTask } from "../../__data__/slices/new-task";
import { NewTask, TaskTypes, Test, taskStrings } from "../../types";
import { useNavigate, useParams } from "react-router-dom";
import { getTaskToEdit } from "../../__data__/slices/tasks";
import { Banner } from "../../components/banner";
import notFind from "./not-find.svg";

const NewTaskSkeleton = () => {
  return (
    <>
      <Row gutter={8}>
        <Col span={14}>
          <Skeleton.Input active block={true} />
        </Col>
        <Col span={2} offset={6}>
          <Skeleton.Button active block={true} />
        </Col>
        <Col span={2}>
          <Skeleton.Button active block={true} />
        </Col>
        <br />
        <br />
        <br />
      </Row>
      <Row>
        <Col span={24}>
          <Skeleton.Input active block={true} />
        </Col>
        <br />
        <br />
        <br />
      </Row>
      <Row>
        <Col span={24}>
          <Skeleton.Input active block={true} />
        </Col>
        <br />
        <br />
        <br />
        <Col span={24}>
          <Skeleton paragraph={{ rows: 7 }} active />
        </Col>
        <br />
        <br />
        <br />
      </Row>
      <Row>
        <Col span={24}>
          <Skeleton.Input active block={true} />
        </Col>
        <br />
        <br />
        <br />
      </Row>
      <Row>
        <Col span={24}>
          <Skeleton.Input active block={true} />
        </Col>
        <br />
        <br />
        <br />
      </Row>
    </>
  );
};

export const Page: FC = () => {
  const { t } = useTranslation();
  const navigate = useNavigate();
  const { id: taskId } = useParams();
  const [showSuccess, setShowSuccess] = useState(false);
  const [activeTab, setActiveTab] = useState("instruction");
  const [title, setTitle] = useState("");
  const [maxAttempts, setMaxAttempts] = useState(5);
  const [type, setType] = useState<TaskTypes>("js");
  const [active, setActive] = useState(false);
  const [description, setDescription] = useState("");
  const [content, setContent] = useState("");
  const [testValues, setTestValues] = useState<Array<Test>>([]);
  const [jsSolution, setJsSolution] = useState<string>("");
  const [htmlSolution, setHtmlSolution] = useState<string>("");
  const [cssSolution, setCssSolution] = useState<string>("");
  const [jsTemplate, setJsTemplate] = useState<string>("");
  const [htmlTemplate, setHtmlTemplate] = useState<string>("");
  const [cssTemplate, setCssTemplate] = useState<string>("");
  const [snapshotNotFind, setSnapshotNotFind] = useState<boolean>(false);

  const dispatch = useAppDispatch();

  const loadingNewTask = useAppSelector((store) => store.newTask.isLoading);
  const errorNewTask = useAppSelector((store) => store.newTask.error);
  const result = useAppSelector((store) => store.newTask.result);
  const loadingTask = useAppSelector((store) => store.tasks.isLoading);
  const errorTask = useAppSelector((store) => store.tasks.error);
  const task = useAppSelector((store) => store.tasks.taskToEdit);

  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    if (result) {
      setShowSuccess(true);
      navigate(`/admin/new-task/${result.id}`);
    }
  }, [navigate, result]);

  useEffect(() => {
    if (loadingNewTask) {
      messageApi.open({
        type: "loading",
        content: t("message.loading"),
        duration: 0,
      });
    } else {
      messageApi.destroy();
    }
  }, [loadingNewTask, messageApi, t]);

  useEffect(() => {
    if (taskId && Number.isInteger(Number(taskId))) {
      dispatch(getTaskToEdit(Number(taskId)));
    }
  }, [dispatch, taskId]);

  useEffect(() => {
    if (taskId && task) {
      setActiveTab("description");
      setTitle(task.title);
      setType(task.type);
      setMaxAttempts(task.maxAttempts);
      setActive(task.active);
      setDescription(task.description);
      setContent(task.content);
      setTestValues(task.test);
      setJsSolution(task.jsSolution);
      setHtmlSolution(task.htmlSolution);
      setCssSolution(task.cssSolution);
      setJsTemplate(task.jsTemplate);
      setHtmlTemplate(task.htmlTemplate);
      setCssTemplate(task.cssTemplate);
    }
  }, [task, taskId]);

  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleMaxAttemptsChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setMaxAttempts(Number(event.target.value));
  };

  const handleDescriptionChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setDescription(event.target.value);
  };

  const handleSendClick = () => {
    const task: NewTask = {
      type,
      title,
      maxAttempts,
      description,
      content,
      active,
      htmlTemplate,
      cssTemplate,
      jsTemplate,
      cssSolution,
      htmlSolution,
      jsSolution,
      test: testValues,
    };
    if (taskId) {
      task.id = Number(taskId);
      dispatch(updateTask(task));
    } else {
      dispatch(addNewTasks(task));
    }
  };

  const handleBackClick = () => {
    navigate("/admin");
  };

  const handleImageError = ({
    currentTarget,
  }: React.SyntheticEvent<HTMLImageElement, Event>) => {
    currentTarget.src = notFind;
    currentTarget.className = styles.image;
    setSnapshotNotFind(true);
  };

  if (errorTask) {
    return <Banner mode="error" />;
  }

  if (loadingTask) {
    return <NewTaskSkeleton />;
  }

  return (
    <>
      {contextHolder}
      {errorNewTask && (
        <Alert
          message="Error"
          description={t("error.loading")}
          type="error"
          closable
        />
      )}
      {showSuccess && (
        <Alert
          message={t("message.success.title")}
          description={t("message.task.add")}
          type="success"
          closable
          onClose={() => {
            setShowSuccess(false);
          }}
        />
      )}
      <Row align="middle">
        <Col span={20}>
          <Typography.Title className={styles.title} level={1}>
            {t("new.task.title")}
          </Typography.Title>
        </Col>
        <Col span={4} className={styles.col}>
          <Space>
            <Button type="default" onClick={handleBackClick}>
              {t("button.back")}
            </Button>
            <Button type="primary" onClick={handleSendClick}>
              {taskId ? t("button.reload") : t("button.send")}
            </Button>
          </Space>
        </Col>
      </Row>
      <Row>
        <Col span={24}>
          <Collapse defaultActiveKey={[activeTab]}>
            <Collapse.Panel
              header={t("new.task.instruction.title")}
              key="instruction"
            >
              <ReactMarkdown children={t("new.task.instruction.content")} />
            </Collapse.Panel>
            {type === "html" && taskId && (
              <Collapse.Panel
                className={
                  snapshotNotFind ? styles.snapshot_not : styles.snapshot
                }
                header={t("new.task.snapshot.title")}
                key="snapshot"
              >
                <img
                  src={
                    process.env.PUBLIC_URL +
                    `/snapshots/${taskId}/__snapshots__/test-js-solution-overlapping-accuracy-99-1-snap.png`
                  }
                  alt="reference snapshot"
                  onError={handleImageError}
                />
              </Collapse.Panel>
            )}
            <Collapse.Panel
              header={t("new.task.description.title")}
              key="description"
            >
              <Row gutter={[8, 8]} align="middle">
                <Col span={16}>
                  <Input
                    value={title}
                    onChange={handleTitleChange}
                    placeholder={
                      t("new.task.description.title.placeholder") as string
                    }
                  />
                </Col>
                <Col span={4}>
                  <Select
                    value={type}
                    onChange={setType}
                    placeholder={t("type")}
                    className={styles.select}
                    options={taskStrings.map((value) => ({
                      value: value,
                      label: value,
                    }))}
                  />
                </Col>
                <Col span={2}>
                  <Tooltip
                    placement="top"
                    title={t("tooltip.max.attempts.text")}
                  >
                    <Input
                      value={maxAttempts}
                      type="number"
                      onChange={handleMaxAttemptsChange}
                    />
                  </Tooltip>
                </Col>{" "}
                <Col span={2} className={styles.col}>
                  <Tooltip placement="top" title={t("tooltip.active.text")}>
                    <Switch
                      checked={active}
                      onChange={setActive}
                      checkedChildren={<CheckOutlined />}
                      unCheckedChildren={<CloseOutlined />}
                    />
                  </Tooltip>
                </Col>
                <Col span={24}>
                  <Input
                    value={description}
                    onChange={handleDescriptionChange}
                    placeholder={
                      t("new.task.description.short.placeholder") as string
                    }
                  />
                </Col>
                <Col span={24}>
                  <MarkdownEditor value={content} onChange={setContent} />
                </Col>
              </Row>
            </Collapse.Panel>
            <Collapse.Panel
              header={t("new.task.solution.title")}
              key="solution"
            >
              <Row gutter={[8, 8]} align="middle">
                {type === "js" && (
                  <Col span={24}>
                    <CodeEditor
                      value={jsSolution}
                      language="JavaScript"
                      mode="solution"
                      onChange={setJsSolution}
                    />
                  </Col>
                )}
                <Col span={24}>
                  <CodeEditor
                    value={htmlSolution}
                    language="HTML"
                    mode="solution"
                    onChange={setHtmlSolution}
                  />
                </Col>
                <Col span={24}>
                  <CodeEditor
                    value={cssSolution}
                    language="CSS"
                    mode="solution"
                    onChange={setCssSolution}
                  />
                </Col>
              </Row>
            </Collapse.Panel>
            <Collapse.Panel
              header={t("new.task.template.title")}
              key="template"
            >
              <Row gutter={[8, 8]} align="middle">
                {type === "js" && (
                  <Col span={24}>
                    <CodeEditor
                      value={jsTemplate}
                      language="JavaScript"
                      mode="template"
                      onChange={setJsTemplate}
                    />
                  </Col>
                )}
                <Col span={24}>
                  <CodeEditor
                    value={htmlTemplate}
                    language="HTML"
                    mode="template"
                    onChange={setHtmlTemplate}
                  />
                </Col>
                <Col span={24}>
                  <CodeEditor
                    value={cssTemplate}
                    language="CSS"
                    mode="template"
                    onChange={setCssTemplate}
                  />
                </Col>
              </Row>
            </Collapse.Panel>
            {type === "js" && (
              <Collapse.Panel header={t("new.task.test.title")} key="test">
                <Row gutter={[8, 8]} align="middle">
                  <Col span={24} className={styles.col}>
                    <Button
                      type="primary"
                      onClick={() => {
                        setTestValues([
                          ...testValues,
                          { content: "", id: testValues.length },
                        ]);
                      }}
                    >
                      {t("button.add")}
                    </Button>
                  </Col>
                  {testValues.map((item, index) => {
                    return (
                      <Col span={24}>
                        <CodeEditor
                          key={index}
                          value={item.content}
                          language="JavaScript"
                          mode="test"
                          index={index}
                          onChange={(value, index) => {
                            const array = [...testValues];
                            array[index ?? 0].content = value;
                            setTestValues(array);
                          }}
                        />
                      </Col>
                    );
                  })}
                </Row>
              </Collapse.Panel>
            )}
          </Collapse>
        </Col>
      </Row>
    </>
  );
};
