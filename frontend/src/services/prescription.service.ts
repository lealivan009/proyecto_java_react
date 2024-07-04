import { RegisterPrescription } from "../models/prescription.models"
import { axiosInstance } from "./axios.config"

export const registerPrescription = async (idAppointment: string, data: RegisterPrescription) =>{
    const res = await axiosInstance.post(`/prescriptions/${idAppointment}`, data);
    return res.data
}

export const getPrescription = async (idAppointment: string) =>{
    const res = await axiosInstance.get(`/prescriptions/${idAppointment}`);
    return res.data
}