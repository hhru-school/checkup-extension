import "antd/dist/reset.css";
import { RouterProvider } from "react-router-dom";
import { router } from "./router";
import { Alert } from "antd";

export const App = () => (
  <Alert.ErrorBoundary>
    <RouterProvider router={router} />
  </Alert.ErrorBoundary>
);
