import React from 'react'
import ReactDOM from 'react-dom/client'
import { AxiosInterceptor } from './interceptors/axios.interceptor.tsx';
import { createBrowserRouter } from 'react-router-dom';
import App from './App.tsx';

const router = createBrowserRouter([
  { 
    path: "/",
    element: <App/>
  }
])

AxiosInterceptor();

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
)
