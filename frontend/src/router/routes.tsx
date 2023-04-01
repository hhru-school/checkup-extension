import { RouteObject } from "react-router-dom";
import * as IndexPage from "../pages/index-page";
import * as ErrorPage from "../pages/error-page";

export const routes: RouteObject[] = [
  {
    path: "/",
    element: <IndexPage.Page />,
  },
  {
    path: "/*",
    element: <ErrorPage.Page />,
  },
];
