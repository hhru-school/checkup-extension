import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import tasks from "../json/tasks.json";
import { Task } from "../../types";

type ResponseType = {
  isLoading: boolean;
  error: string | null;
  tasks: Array<Task>;
};

const initialState: ResponseType = tasks as ResponseType;

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
        state.error = action.error.message as string;
        state.tasks = [];
      });
  },
});

export const { actions, reducer } = slice;
