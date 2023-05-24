export const taskStrings = ["JS", "HTML"];
export type TaskTypes = (typeof taskStrings)[number];

export type Task = {
  id?: number;
  type: TaskTypes;
  title: string;
  description: string;
  // step: number;
  // status: TaskProcess;
  content: string;
  active: boolean;
  htmlTemplate: string;
  cssTemplate: string;
  jsTemplate: string;
};

export enum TaskProcess {
  PROCESS = 0,
  ERROR,
}

export type SolutionToSend = {
  problemId: number;
  htmlContent: string;
  cssContent: string;
  jsContent: string;
};

export type SolutionShort = {
  id: number;
  status: string;
  title: string;
  date: string;
};

export type SolutionFull = {
  id: number;
  status: string;
  title: string;
  date: string;
  htmlContent: string;
  cssContent: string;
  jsContent: string;
};
