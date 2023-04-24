import { createDraftSafeSelector } from "@reduxjs/toolkit";
import { StoreType } from "../store";

const rootSelector = (state: StoreType) => state;
export const getTasks = createDraftSafeSelector(
  rootSelector,
  (state) => state.tasks.tasks
);
