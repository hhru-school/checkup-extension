import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { NewTask, Task, TaskShort, TasksToEdit } from "../../types";
import axios from "axios";
import { endpoints } from "../constants/endpoints";

type ResponseType = {
  isLoading: boolean;
  error: string | null;
  tasks: Array<TaskShort>;
  task: Task | null;
  tasksToEdit: TasksToEdit | null;
  taskToEdit: NewTask | null;
};

const initialState: ResponseType = {
  isLoading: false,
  error: null,
  tasks: [],
  task: null,
  tasksToEdit: null,
  taskToEdit: null,
};

export const getTasks = createAsyncThunk<Array<TaskShort>>(
  "tasks/all",
  async () => {
    const response = await axios.get(endpoints.getTasks(), {
      transformResponse: [
        (data) => {
          try {
            const parsedData = JSON.parse(data).problems;
            return parsedData.map((item: TaskShort) => ({
              ...item,
              active: true,
            }));
          } catch (error) {
            return data.problems;
          }
        },
      ],
    });
    return response.data;
  }
);

export const getTasksToEdit = createAsyncThunk(
  "tasks/all/edit",
  async ({ page, size }: { page: number; size: number }) => {
    const response = await axios.get(endpoints.getTasksToEdit(page, size), {
      withCredentials: true,
    });
    return response.data;
  }
);

export const getTask = createAsyncThunk("tasks/get", async (id: number) => {
  const response = await axios.get(endpoints.getTask(id));
  return response.data;
});

export const getTaskToEdit = createAsyncThunk(
  "tasks/get/edit",
  async (id: number) => {
    const response = await axios.get(endpoints.getTaskToEdit(id), {
      withCredentials: true,
    });
    return response.data;
  }
);

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
      })
      .addCase(getTasksToEdit.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(getTasksToEdit.fulfilled, (state, action) => {
        state.isLoading = false;
        state.tasksToEdit = action.payload;
        state.error = null;
      })
      .addCase(getTasksToEdit.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.tasksToEdit = null;
      })
      .addCase(getTaskToEdit.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(getTaskToEdit.fulfilled, (state, action) => {
        state.isLoading = false;
        state.taskToEdit = action.payload;
        state.error = null;
      })
      .addCase(getTaskToEdit.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.taskToEdit = null;
      });
  },
});

export const { actions, reducer } = slice;
