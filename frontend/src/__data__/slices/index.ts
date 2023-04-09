import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";

type TasksType = {
  isLoading: boolean;
  error: string | null;
  tasks: Array<{
    id: number;
    type: "js" | "html";
    title: string;
    description: string;
    step: number;
    status: "process" | "error";
    content: string;
  }>;
};

const initialState: TasksType = {
  isLoading: false,
  error: null,
  tasks: [
    {
      id: 1,
      type: "js",
      title: "Интересная задача по JS",
      description: "Задача на проверку базовых знаний JS, DOM API",
      step: 0,
      status: "process",
      content: `Есть ферма животных, у всех животных есть имена и возраст. Животные бывают разных типов: Кошки, Собаки, Коровы. У каждого животного могут быть дети. Если животное является родителем этих детей, в свою очередь глубина семейного древа может достигать N либо 0.
            Опишите структуры данных для фермы животных и напишите функцию, которая делает подсчёт всех возрастов животных и выводит общий возраст для всей фермы.`,
    },
    {
      id: 2,
      type: "html",
      title: "Интересная задача по HTML и CSS",
      description: "Задача на проверку базовых знаний HTML и CSS",
      step: 1,
      status: "error",
      content: `Исправьте ошибки в приведенном коде.

            <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">
            <html>
             <head>
              <meta http-equiv="Content-Type" content="text/html; charset=utf-1">
             <body>
              <h11 align="justify">Галион</h1>
              <p align="justify">
               <strong>Галион</b> - большое трехмачтовое судно особо прочной постройки, 
               снабженное тяжелой артиллерией.</br> 
               Эти суда служили для перевозки товаров и драгоценных металлов из испанских и португальских 
               колоний в Европу.</p>
               <hr>
               <blockquote>Флагманский корабль был мощным <i>галионом</i>, вооруженным сорока восьмью
                большими пушками и восьмью малыми.</blockquote>
               </hr>
              </p>
             </body>
            </html>`,
    },
  ],
};

// TODO: implement fetch from back, or use RTK?
export const fetchTasks = createAsyncThunk("tasks/fetchTasks", async () => {
  return initialState.tasks;
});

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
