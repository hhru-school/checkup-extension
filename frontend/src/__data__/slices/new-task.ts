import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { NewTask, Task } from "../../types";
import { endpoints } from "../constants/endpoints";

type NewTasksType = {
  isLoading: boolean;
  error: string | null;
  result: NewTask | null;
};

const initialState: NewTasksType = {
  isLoading: false,
  error: null,
  result: null,
};

export const addNewTasks = createAsyncThunk(
  "tasks/create",
  async (task: Task) => {
    const response = await axios.post(endpoints.newTask(), task, {
      withCredentials: true,
    });
    return response.data;
  }
);

export const updateTask = createAsyncThunk(
  "tasks/update",
  async (task: Task) => {
    const response = await axios.put(endpoints.newTask(), task, {
      withCredentials: true,
    });
    return response.data;
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
        state.result = null;
      });
  },
});

export const { actions, reducer } = slice;
