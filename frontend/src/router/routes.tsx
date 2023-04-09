import { RouteObject } from "react-router-dom";
import * as IndexPage from "../pages/index-page";
import * as ErrorPage from "../pages/error-page";
import * as TaskEditPage from "../pages/task-edit-page";

export const routes: RouteObject[] = [
  {
    path: "/",
    element: <IndexPage.Page />,
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
