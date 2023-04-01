import { Result } from "antd";
import { isRouteErrorResponse, useRouteError } from "react-router-dom";

type Props = {
  error?: Error;
};

export const Page = ({ error: propsError }: Props) => {
  const routeError = useRouteError();
  const error = propsError || routeError;

  let status: 403 | 404 | 500 = 500;
  let title = "Ошибка!";

  if (isRouteErrorResponse(error)) {
    if (error.status === 404) {
      status = 404;
      title = "Страница не найдена";
    }

    if (error.status === 401) {
      status = 403;
      title = "Ошибка авторизации";
    }

    if (error.status === 503) {
      status = 500;
      title = "Ошибка API";
    }
  }

  return <Result status={status} title={title} />;
};
