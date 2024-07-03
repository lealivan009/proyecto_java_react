import { useUser } from "../hooks/useUser";
import { Container, Paper } from "@mui/material";

export default function Home() {
  const {user, getUser} = useUser()
  const userId = localStorage.getItem("userId");

  getUser(userId);

  return (
    <Container>
      <Paper>
        <div>{user?.name}</div>
        <div>{user?.lastname}</div>
        <div>{user?.email}</div>
      </Paper>
    </Container>
  );
}
