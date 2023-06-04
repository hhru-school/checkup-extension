// const base = "http://localhost:3100";
const base = "http://localhost:8081";

export const endpoints = {
  history: (id: number) => `${base}/submissions/problem/${id}`,
  newTask: () => `${base}/problem`,
  updateTask: () => `${base}/problem`,
  newSolution: () => `${base}/submissions`,
  getSolution: (id: number) => `${base}/submissions/${id}`,
  getSolutionStatus: (id: number) => `${base}/submissions/problem/${id}`,
  getTasks: () => `${base}/problems/info`,
  getTask: (id: number) => `${base}/problems/${id}`,
};
