import axios from "axios"

const BASE_URL = "http://localhost:8080/api"

export const getMedicals = async () =>{
    const res = await axios.get(`${BASE_URL}/medicals`);
    return res.data;
}