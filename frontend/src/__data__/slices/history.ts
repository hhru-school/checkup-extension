import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { Solution } from "../../types";
import axios from "axios";

type ResponseType = {
  isLoading: boolean;
  error: string | null;
  solutions: Array<Solution>;
};

const initialState: ResponseType = {
  isLoading: false,
  error: null,
  solutions: [
    {
      id: 5,
      status: "check",
      title: "Текущее решение",
      date: "2023-04-09 15:22",
    },
    {
      id: 4,
      status: "success",
      title: "Попытка №5",
      date: "2023-04-09 15:22",
    },
    {
      id: 3,
      status: "success",
      title: "Попытка №4",
      date: "2023-04-09 15:22",
    },
    {
      id: 2,
      status: "fault",
      title: "Попытка №3",
      date: "2023-04-09 15:22",
    },
    {
      id: 1,
      status: "fault",
      title: "Попытка №2",
      date: "2023-04-09 15:22",
    },
    {
      id: 0,
      status: "fault",
      title: "Попытка №1",
      date: "2023-04-09 15:22",
    },
  ],
};

export const fetchHistory = createAsyncThunk(
  "history/fetch",
  async (problemId: number) => {
    const response = await axios.get(`/solutions/${problemId}`);
    return response.data;
  }
);

const slice = createSlice({
  name: "history",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(fetchHistory.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(fetchHistory.fulfilled, (state, action) => {
        state.isLoading = false;
        state.solutions = action.payload;
        state.error = null;
      })
      .addCase(fetchHistory.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.solutions = [];
      });
  },
});

export const { actions, reducer } = slice;
