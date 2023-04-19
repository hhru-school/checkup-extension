import { RouteObject } from "react-router-dom";
import * as IndexPage from "../pages/index-page";
import * as ErrorPage from "../pages/error-page";
import * as TaskEditPage from "../pages/task-edit-page";
import * as AdminPage from "../pages/index-page/admin";

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
    path: "/:id",
    element: <TaskEditPage.Page />,
  },
  {
    path: "/*",
    element: <ErrorPage.Page />,
  },
];
