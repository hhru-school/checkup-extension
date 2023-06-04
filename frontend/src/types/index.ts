export const taskStrings = ["js", "html"] as const;
export type TaskTypes = (typeof taskStrings)[number];

export const statusStrings = ["checked", "success", "fault"] as const;
export type StatusTypes = (typeof statusStrings)[number];

export type Test = {
  id?: number;
  content: string;
};

export type Task = {
  id?: number;
  type: TaskTypes;
  title: string;
  maxAttempts: number;
  description: string;
  content: string;
  active: boolean;
  htmlTemplate: string;
  cssTemplate: string;
  jsTemplate: string;
};

export type NewTask = {
  id?: number;
  type: TaskTypes;
  title: string;
  maxAttempts: number;
  description: string;
  content: string;
  active: boolean;
  htmlSolution: string;
  cssSolution: string;
  jsSolution: string;
  htmlTemplate: string;
  cssTemplate: string;
  jsTemplate: string;
  test?: Array<Test>;
};

export enum TaskProcess {
  PROCESS = 0,
  ERROR,
}

export type SolutionToSend = {
  problemId: number;
  htmlPart: string;
  cssPart: string;
  jsPart: string;
};

export type SolutionShort = {
  submissionId: number;
  status: string;
  title: string;
  date: string;
};

export type SolutionFull = {
  id: number;
  problemId: number;
  statusId: string;
  date: string;
  htmlPart: string;
  cssPart: string;
  jsPart: string;
};
