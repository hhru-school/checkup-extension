import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import axios from "axios";
import { SolutionToSend } from "../../types";

type ResponseType = {
  isLoading: boolean;
  error: string | null;
  result: string;
};

const initialState: ResponseType = {
  isLoading: false,
  error: null,
  result: "OK",
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
    await axios.post(`http://localhost:8081/solutions`, solution);
    return "OK";
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
        console.log(action);
        state.isLoading = false;
        state.error = action.error.message as string;
        state.result = "ERROR";
      });
  },
});

export const { actions, reducer } = slice;
