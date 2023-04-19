import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import tasks from "../json/tasks.json";

export enum TaskTypes {
  JS = 0,
  HTML,
}

export enum TaskProcess {
  PROCESS = 0,
  ERROR,
}

export type Task = {
  id: number;
  type: TaskTypes;
  title: string;
  description: string;
  step: number;
  status: TaskProcess;
  content: string;
  active: boolean;
};

type TasksType = {
  isLoading: boolean;
  error: string | null;
  tasks: Array<Task>;
};

const initialState: TasksType = tasks as TasksType;

// TODO: implement fetch from back, or use RTK?
export const fetchTasks = createAsyncThunk<Array<Task>>(
  "tasks/fetchTasks",
  async () => {
    return tasks.tasks as Array<Task>;
  }
);

const slice = createSlice({
  name: "tasks",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchTasks.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(fetchTasks.fulfilled, (state, action) => {
        state.isLoading = false;
        state.tasks = action.payload;
        state.error = null;
      })
      .addCase(fetchTasks.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.payload as string;
        state.tasks = [];
      });
  },
});

export const { actions, reducer } = slice;
