import { useEffect, useState } from "react";
import { User } from "../models/user.models";
import { getUserById } from "../services/user.service";

export const useUser = () =>{
    const [user, setUser] = useState<User|null>();

    const getUser = ( id: string|undefined ) => {
        useEffect(()=>{
            getUserById(id)
            .then(data => setUser(data))
        },[])
    }

    return {
        user,
        getUser
    }
}
