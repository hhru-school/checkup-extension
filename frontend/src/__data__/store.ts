import { configureStore } from "@reduxjs/toolkit";
import { useDispatch, useSelector, TypedUseSelectorHook } from "react-redux";
import { reducer as tasksReducer } from "./slices/tasks";
import { reducer as newTaskReducer } from "./slices/new-task";
import { reducer as solutionReducer } from "./slices/solution";
import { reducer as historyReducer } from "./slices/history";
import { combineReducers } from "redux";

const rootReducer = combineReducers({
  tasks: tasksReducer,
  newTask: newTaskReducer,
  solution: solutionReducer,
  history: historyReducer,
});

const store = configureStore({
  reducer: rootReducer,
});

export type StoreType = ReturnType<typeof store.getState>;

export type AppDispatch = typeof store.dispatch;

export const useAppDispatch: () => AppDispatch = useDispatch;

export const useAppSelector: TypedUseSelectorHook<StoreType> = useSelector;

export default store;
