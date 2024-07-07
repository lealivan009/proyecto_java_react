import { LoginUser, RegisterUser } from "../models/user.models";
import { axiosInstance } from "./axios.config";

export const getAllUser = async () => {
   const res = await axiosInstance.get(`/users`);
   return res.data;
}

export const getUserById = async ( id: string | null | undefined ) => {
   const res = await axiosInstance.get(`/users/${id}`);
   return res.data;
}

export const registerUser = async ( user: RegisterUser ) => {
   const res = await axiosInstance.post(`/users/register`, user);
   return res.data;
}

export const loginUser = async ( user: LoginUser ) => {
   const res = await axiosInstance.post(`/users/login`, user);
   return res.data;
}

export const getAppointments = async ( userId : string | null | undefined) => {
   const res = await axiosInstance.get(`/users/${userId}/appointments`);
   return res.data;
} 