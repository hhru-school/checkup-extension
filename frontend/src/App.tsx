import "antd/dist/reset.css";
import {
  Outlet,
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";
import { Alert, ConfigProvider } from "antd";
import { CheckupContainer } from "./components/legacy/CheckupContainer";
import { CheckupHeader } from "./components/legacy/CheckupHeader";
import * as IndexPage from "./pages/index-page";
import * as ErrorPage from "./pages/error-page";
import * as TaskEditPage from "./pages/task-solution-page";
import * as AdminPage from "./pages/index-page/admin";
import * as NewTaskPage from "./pages/new-task-page";

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

export const router = createBrowserRouter(
  createRoutesFromElements(
    <Route
      element={
        <CheckupContainer header={<CheckupHeader />}>
          <Outlet />
        </CheckupContainer>
      }
    >
      <Route path="/" element={<IndexPage.Page />} />
      <Route path="admin" element={<AdminPage.Admin />} />
      <Route path="admin/new-task" element={<NewTaskPage.Page />} />
      <Route path="admin/new-task/:id" element={<NewTaskPage.Page />} />
      <Route path="/:id" element={<TaskEditPage.Page />} />
      <Route path="*" element={<ErrorPage.Page />} />
    </Route>
  )
);
