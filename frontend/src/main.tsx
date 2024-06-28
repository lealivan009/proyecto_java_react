import React from 'react'
import ReactDOM from 'react-dom/client'
import { AxiosInterceptor } from './interceptors/axios.interceptor.tsx';

import { App } from "./App.tsx";

AxiosInterceptor();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
