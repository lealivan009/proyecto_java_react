import { useEffect, useState } from "react";
import { getMedicalById } from "../services/medical.service";
import { SpecialistSchedules } from "../models/medical.models";

export const useMedical = () =>{
    const [medical, setMedical] = useState<SpecialistSchedules>();

    const getMedical = ( id: string) => {
        useEffect(()=>{
            getMedicalById(id)
            .then(data => setMedical(data))
        },[])
    }
    return {
        medical,
        getMedical
    }
}