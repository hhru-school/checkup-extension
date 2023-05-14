import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Task } from "./tasks";
import axios from "axios";

type NewTasksType = {
  isLoading: boolean;
  error: string | null;
  result: string;
};

const initialState: NewTasksType = {
  isLoading: false,
  error: null,
  result: "OK",
};

export const addNewTasks = createAsyncThunk(
  "tasks/create",
  async (task: Task) => {
    await axios.post("/tasks/create", task);
    return "OK";
  }
);

const slice = createSlice({
  name: "newTask",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(addNewTasks.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(addNewTasks.fulfilled, (state, action) => {
        state.isLoading = false;
        state.result = action.payload;
        state.error = null;
      })
      .addCase(addNewTasks.rejected, (state, action) => {
        console.log(action);
        state.isLoading = false;
        state.error = action.error.message as string;
        state.result = "ERROR";
      });
  },
});

export const { actions, reducer } = slice;