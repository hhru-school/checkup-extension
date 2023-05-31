import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { SolutionShort } from "../../types";
import axios from "axios";
import { endpoints } from "../constants/endpoints";

type ResponseType = {
  isLoading: boolean;
  error: string | null;
  solutions: Array<SolutionShort>;
  currentSolutionId: number;
};

const initialState: ResponseType = {
  isLoading: false,
  error: null,
  solutions: [],
  currentSolutionId: -1,
};

export const fetchHistory = createAsyncThunk(
  "history/fetch",
  async (problemId: number) => {
    const response = await axios.get(endpoints.history(problemId), {
      headers: {
        withCredentials: true,
        Cookie: "userToker='true'",
      },
    });
    return response.data;
  }
);

const slice = createSlice({
  name: "history",
  initialState,
  reducers: {
    setCurrentSolution: (state, action) => {
      state.currentSolutionId = action.payload;
    },
  },
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
