import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Task, TaskShort } from "../../types";
import axios from "axios";
import { endpoints } from "../constants/endpoints";

type ResponseType = {
  isLoading: boolean;
  error: string | null;
  tasks: Array<TaskShort>;
  task: Task | null;
};

const initialState: ResponseType = {
  isLoading: false,
  error: null,
  tasks: [],
  task: null,
};

export const getTasks = createAsyncThunk<Array<TaskShort>>(
  "tasks/all",
  async () => {
    const response = await axios.get(endpoints.getTasks(), {
      transformResponse: [
        (data) => {
          try {
            const parsedData = JSON.parse(data);
            return parsedData.map((item: TaskShort) => ({
              ...item,
              active: true,
            }));
          } catch (error) {
            return data;
          }
        },
      ],
    });
    return response.data;
  }
);

export const getTask = createAsyncThunk("tasks/get", async (id: number) => {
  const response = await axios.get(endpoints.getTask(id));
  return response.data;
});

const slice = createSlice({
  name: "tasks",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(getTasks.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(getTasks.fulfilled, (state, action) => {
        state.isLoading = false;
        state.tasks = action.payload;
        state.error = null;
      })
      .addCase(getTasks.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.tasks = [];
      })
      .addCase(getTask.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(getTask.fulfilled, (state, action) => {
        state.isLoading = false;
        state.task = action.payload;
        state.error = null;
      })
      .addCase(getTask.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.task = null;
      });
  },
});

export const { actions, reducer } = slice;
