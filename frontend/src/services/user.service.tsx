import axios from "axios";

const BASE_URL = "http://localhost:8080/api";


export const getAllUser = async () => {
   const res = await axios.get(`${BASE_URL}/users`);
   return res.data;
}

export const getUserById = async ( {id}:any ) => {
   const res = await axios.get(`${BASE_URL}/users/${id}`);
   return res.data;
}