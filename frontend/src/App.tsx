import "antd/dist/reset.css";
import { RouterProvider } from "react-router-dom";
import { router } from "./router";
import { Alert, ConfigProvider } from "antd";

export const App = () => (
  <ConfigProvider
    theme={{
      token: {
        colorPrimary: "#00b96b",
      },
    }}
  >
    <Alert.ErrorBoundary>
      <RouterProvider router={router} />
    </Alert.ErrorBoundary>
  </ConfigProvider>
);
