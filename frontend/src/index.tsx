import React from "react";
import ReactDOM from "react-dom/client";
import { App } from "./App";
import { CheckupHeader } from "./components/legacy/CheckupHeader";
import { CheckupContainer } from "./components/legacy/CheckupContainer";
import { Provider } from "react-redux";
import store from "./__data__/store";
import "./i18n";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);

root.render(
  <React.StrictMode>
    <Provider store={store}>
      <CheckupContainer header={<CheckupHeader />}>
        <App />
      </CheckupContainer>
    </Provider>
  </React.StrictMode>
);
