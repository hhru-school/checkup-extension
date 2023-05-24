const base = "http://localhost:8081";

export const endpoints = {
  history: (id: number) => `${base}/submissions/task/${id}`,
  newTask: () => `${base}/task`,
  newSolution: () => `${base}/submissions`,
  getSolution: (id: number) => `${base}/submissions/${id}`,
  tasks: () => `${base}/tasks`,
};