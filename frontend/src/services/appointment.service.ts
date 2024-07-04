import { RegisterAppointment } from "../models/appointment.models";
import { axiosInstance } from "./axios.config";

export const registerAppoinment = async (data: RegisterAppointment) =>{
    const res = await axiosInstance.post(`/appointments`, data);
    return res.data;
}