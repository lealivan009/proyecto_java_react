import { Container, Typography } from "@mui/material";
import LoginUserForm from "../components/form/LoginUserForm";

export default function Login() {
  return (
    <>
      <Typography margin={3} variant="h5" textAlign={"center"}>
        Ingresar cuenta
      </Typography>
      <Container maxWidth="md" >
        <LoginUserForm />
      </Container>
    </>
  );
}
