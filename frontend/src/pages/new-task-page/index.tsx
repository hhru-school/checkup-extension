import {
  Typography,
  Col,
  Row,
  Collapse,
  Input,
  Select,
  Switch,
  Space,
  Tooltip,
  Button,
} from "antd";
import { CheckOutlined, CloseOutlined } from "@ant-design/icons";
import styles from "./index.module.css";
import { useTranslation } from "react-i18next";
import ReactMarkdown from "react-markdown";
import { TaskTypes } from "../../__data__/slices";
import { MarkdownEditor } from "../../components/md-editor";
import { CodeEditor } from "../../components/code-editor";
import { FC, useState } from "react";

export const Page = () => {
  const { t } = useTranslation();
  const [title, setTitle] = useState("");
  const [type, setType] = useState(TaskTypes[TaskTypes.JS]);
  const [active, setActive] = useState(false);
  const [description, setDescription] = useState("");
  const [content, setContent] = useState("");
  const [testValues, setTestValues] = useState<Array<string>>([""]);
  const [solutionJSValue, setSolutionJSValue] = useState<string>("");
  const [solutionHTMLValue, setSolutionHTMLValue] = useState<string>("");
  const [solutionCSSValue, setSolutionCSSValue] = useState<string>("");
  const [templateJSValue, setTemplateJSValue] = useState<string>("");
  const [templateHTMLValue, setTemplateHTMLValue] = useState<string>("");
  const [templateCSSValue, setTemplateCSSValue] = useState<string>("");

  const handleTitleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setTitle(event.target.value);
  };

  const handleDescriptionChange = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    setDescription(event.target.value);
  };

  const handleSendClick = () => {};

  return (
    <>
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
                    options={Object.values(TaskTypes)
                      .filter((value) => typeof value === "string")
                      .map((value) => ({ value: value, label: value }))}
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
                    value={solutionJSValue}
                    language="JavaScript"
                    mode="solution"
                    onChange={setSolutionJSValue}
                  />
                </Col>
                <Col span={24}>
                  <CodeEditor
                    value={solutionHTMLValue}
                    language="HTML"
                    mode="solution"
                    onChange={setSolutionHTMLValue}
                  />
                </Col>
                <Col span={24}>
                  <CodeEditor
                    value={solutionCSSValue}
                    language="CSS"
                    mode="solution"
                    onChange={setSolutionCSSValue}
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
                    value={templateJSValue}
                    language="JavaScript"
                    mode="template"
                    onChange={setTemplateJSValue}
                  />
                </Col>
                <Col span={24}>
                  <CodeEditor
                    value={templateHTMLValue}
                    language="HTML"
                    mode="template"
                    onChange={setTemplateHTMLValue}
                  />
                </Col>
                <Col span={24}>
                  <CodeEditor
                    value={templateCSSValue}
                    language="CSS"
                    mode="template"
                    onChange={setTemplateCSSValue}
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
