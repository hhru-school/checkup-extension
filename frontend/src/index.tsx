import React from "react";
import ReactDOM from "react-dom/client";
import { App } from "./App";
import { CheckupHeader } from "./components/legacy/CheckupHeader";
import { CheckupContainer } from "./components/legacy/CheckupContainer";

const root = ReactDOM.createRoot(
  document.getElementById("root") as HTMLElement
);

root.render(
  <React.StrictMode>
    <CheckupContainer header={<CheckupHeader />}>
      <App />
    </CheckupContainer>
  </React.StrictMode>
);
