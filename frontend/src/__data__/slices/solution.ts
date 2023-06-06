import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { SolutionFull, SolutionToSend } from "../../types";
import { endpoints } from "../constants/endpoints";

type SolutionSliceType = {
  isLoading: boolean;
  error: string | null;
  result: SolutionFull | null;
  solution: SolutionFull | null;
};

const initialState: SolutionSliceType = {
  isLoading: false,
  error: null,
  result: null,
  solution: null,
};

export const sendSolution = createAsyncThunk(
  "solution/send",
  async ({
    solution,
    taskId,
  }: {
    solution: SolutionToSend;
    taskId: number;
  }) => {
    const response = await axios.post(endpoints.newSolution(), solution, {
      withCredentials: true,
    });
    return response.data;
  }
);

export const getSolution = createAsyncThunk(
  "solution/get",
  async (id: number) => {
    const response = await axios.get(endpoints.getSolution(id), {
      withCredentials: true,
    });
    return response.data;
  }
);

const slice = createSlice({
  name: "solution",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(sendSolution.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(sendSolution.fulfilled, (state, action) => {
        state.isLoading = false;
        state.result = action.payload;
        state.error = null;
      })
      .addCase(sendSolution.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.result = null;
      })
      .addCase(getSolution.pending, (state, action) => {
        state.isLoading = true;
      })
      .addCase(getSolution.fulfilled, (state, action) => {
        state.isLoading = false;
        state.solution = action.payload;
        state.error = null;
      })
      .addCase(getSolution.rejected, (state, action) => {
        state.isLoading = false;
        state.error = action.error.message as string;
        state.solution = null;
      });
  },
});

export const { actions, reducer } = slice;
