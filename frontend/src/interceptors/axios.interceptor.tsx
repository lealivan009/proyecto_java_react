import axios from "axios";

export const AxiosInterceptor = () => {
  axios.interceptors.request.use((request) => {
    return request;
  });

  axios.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      return Promise.reject(error);
    }
  );
};
