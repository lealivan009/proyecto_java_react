import { Avatar, Box, Divider, Paper, Typography } from "@mui/material";
import { useUser } from "../hooks/useUser";
import { initialsLetters } from "../utils/user-utils";

export default function Home() {
  const {user, getUser} = useUser()
  const userId = localStorage.getItem("userId");

  getUser(userId);
  const avatar = initialsLetters(user);

  return (
    <Box maxWidth="sm"  >
      <Paper elevation={3} >
        <Box sx={{padding:"10px", display: "flex", flexDirection: "column", gap: "5px"}} >
        <Avatar >{avatar}</Avatar>
        <Divider />
        <Typography> <strong> Nombre:</strong> {user?.name} </Typography>
        <Typography> <strong> Apellido:</strong> {user?.lastname} </Typography>
        <Typography> <strong> Correo:</strong> {user?.email} </Typography>
        <Typography> <strong> N° Documento:</strong> {user?.dni} </Typography>
        </Box>
      </Paper>
    </Box>
  );
}
