
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { User } from "../models/user.models";
import { getUserById } from "../services/user.service";
import { Container } from "@mui/material";

export default function Home(){
  const [user, setUser] = useState<User|null>(null);
  const { id } = useParams<string>();

  useEffect(() =>{
    const fetchData = async () =>{
      try {
        const user = await getUserById(id)
        setUser(user)
      } catch (error) {
        console.log(error)
      }
    }
    fetchData()
  }, [])

  return (
    <Container>
      <div>{user?.email}</div>
    </Container>
  );
}
