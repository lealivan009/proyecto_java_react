import axios from "axios";
import { RegisterUser } from "../models/user.models";

const BASE_URL = "http://localhost:8080/api";


export const getAllUser = async () => {
   const res = await axios.get(`${BASE_URL}/users`);
   return res.data;
}

export const getUserById = async ( {id}:any ) => {
   const res = await axios.get(`${BASE_URL}/users/${id}`);
   return res.data;
}
export const registerUser = async ( user: RegisterUser ) => {
   const res = await axios.post(`${BASE_URL}/users/register`, user);
   return res.data;
}