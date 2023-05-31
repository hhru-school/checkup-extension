const base = "http://localhost:3100";

export const endpoints = {
  history: (id: number) => `${base}/submissions/task/${id}`,
  newTask: () => `${base}/task`,
  newSolution: () => `${base}/submissions`,
  getSolution: (id: number) => `${base}/submissions/${id}`,
  getTasks: () => `${base}/tasks`,
  getTask: (id: number) => `${base}/tasks/${id}`,
};
