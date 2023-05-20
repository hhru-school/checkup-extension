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
} from "antd";
import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import styles from "./index.module.css";
import { useTranslation } from "react-i18next";
import ReactMarkdown from "react-markdown";
import { MarkdownEditor } from "../../components/md-editor";
import { CodeEditor } from "../../components/code-editor";
import { FC, useEffect, useState } from "react";
import { useAppDispatch, useAppSelector } from "../../__data__/store";
import { addNewTasks } from "../../__data__/slices/new-task";
import { Task, TaskTypes, taskStrings } from "../../types";

export const Page: FC = () => {
  const { t } = useTranslation();
  const [title, setTitle] = useState("");
  const [type, setType] = useState<TaskTypes>("JS");
  const [active, setActive] = useState(false);
  const [description, setDescription] = useState("");
  const [content, setContent] = useState("");
  const [testValues, setTestValues] = useState<Array<string>>([""]);
  const [jsSolution, setJsSolution] = useState<string>("");
  const [htmlSolution, setHtmlSolution] = useState<string>("");
  const [cssSolution, setCssSolution] = useState<string>("");
  const [jsTemplate, setJsTemplate] = useState<string>("");
  const [htmlTemplate, setHtmlTemplate] = useState<string>("");
  const [cssTemplate, setCssTemplate] = useState<string>("");

  const dispatch = useAppDispatch();

  const loading = useAppSelector((store) => store.newTask.isLoading);
  const error = useAppSelector((store) => store.newTask.error);
  const result = useAppSelector((store) => store.newTask.result);

  const [messageApi, contextHolder] = message.useMessage();

  useEffect(() => {
    if (loading) {
      messageApi.open({
        type: "loading",
        content: "Action in progress..",
        duration: 0,
      });
    } else {
      messageApi.destroy();
    }
  }, [loading, messageApi]);

  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleDescriptionChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setDescription(event.target.value);
  };

  const handleSendClick = () => {
    const task: Task = {
      type,
      title,
      description,
      content,
      active,
      htmlTemplate,
      cssTemplate,
      jsTemplate,
    };
    dispatch(addNewTasks(task));
  };

  return (
    <>
      {contextHolder}
      {error && (
        <Alert message="Error" description={result} type="error" closable />
      )}
      <Row align="middle">
        <Col span={20}>
          <Typography.Title className={styles.title} level={1}>
            {t("new.task.title")}
          </Typography.Title>
        </Col>
        <Col span={4} className={styles.col}>
          <Button type="primary" onClick={handleSendClick}>
            {t("button.send")}
          </Button>
        </Col>
      </Row>
      <Row>
        <Col span={24}>
          <Collapse defaultActiveKey={["instruction"]}>
            <Collapse.Panel
              header={t("new.task.instruction.title")}
              key="instruction"
            >
              <ReactMarkdown children={t("new.task.instruction.content")} />
            </Collapse.Panel>
            <Collapse.Panel
              header={t("new.task.description.title")}
              key="description"
            >
              <Row gutter={[8, 8]} align="middle">
                <Col span={18}>
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
                <Col span={2} className={styles.col}>
                  <Tooltip placement="top" title={t("tooltep.active.text")}>
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
                <Col span={24}>
                  <CodeEditor
                    value={jsSolution}
                    language="JavaScript"
                    mode="solution"
                    onChange={setJsSolution}
                  />
                </Col>
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
                <Col span={24}>
                  <CodeEditor
                    value={jsTemplate}
                    language="JavaScript"
                    mode="template"
                    onChange={setJsTemplate}
                  />
                </Col>
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
            <Collapse.Panel header={t("new.task.test.title")} key="test">
              <Row gutter={[8, 8]} align="middle">
                <Col span={24} className={styles.col}>
                  <Button
                    type="primary"
                    onClick={() => {
                      setTestValues([...testValues, ""]);
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
                        value={item}
                        language="JavaScript"
                        mode="test"
                        index={index}
                        onChange={(value, index) => {
                          const array = [...testValues];
                          array[index ?? 0] = value;
                          setTestValues(array);
                        }}
                      />
                    </Col>
                  );
                })}
              </Row>
            </Collapse.Panel>
          </Collapse>
        </Col>
      </Row>
    </>
  );
};
