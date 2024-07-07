import { RegisterMedical } from "../models/medical.models";
import { axiosInstance } from "./axios.config"; 

export const getMedicals = async () =>{
    const res = await axiosInstance.get(`/medicals`);
    return res.data;
}

export const getMedicalById = async (id: string)  => {
    const res = await axiosInstance.get(`/medicals/${id}`);
    return res.data;
}

export const registerMedicals = async (data: RegisterMedical) =>{
    const res = await axiosInstance.post(`/medicals/register`, data);
    return res.data;
}