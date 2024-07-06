import axios from "axios";
import { getValidationError } from "../utils/validation-errors";

export const axiosInstance = axios.create({
    baseURL:"http://localhost:8080/api"
})

axiosInstance.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      console.log("axios error", getValidationError(error.code));  
      return Promise.reject(error);
    }
  );