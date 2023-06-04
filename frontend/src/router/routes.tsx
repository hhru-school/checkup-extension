import { RouteObject } from "react-router-dom";
import * as IndexPage from "../pages/index-page";
import * as ErrorPage from "../pages/error-page";
import * as TaskEditPage from "../pages/task-solution-page";
import * as AdminPage from "../pages/index-page/admin";
import * as NewTaskPage from "../pages/new-task-page";

export const routes: RouteObject[] = [
  {
    path: "/",
    element: <IndexPage.Page />,
  },
  {
    path: "/admin",
    element: <AdminPage.Admin />,
  },
  {
    path: "/admin/new-task",
    element: <NewTaskPage.Page />,
  },
  {
    path: "/admin/new-task/:id",
    element: <NewTaskPage.Page />,
  },
  {
    path: "/:id",
    element: <TaskEditPage.Page />,
  },
  {
    path: "/*",
    element: <ErrorPage.Page />,
  },
];
