import { Container, Typography } from "@mui/material";
import RegisterUserForm from "../components/form/RegisterUserForm";
import { Link } from "react-router-dom";

export default function Register() {
  return (
    <>
      <Typography margin={3} variant="h5" textAlign={"center"}>
        Registrate como usuario
      </Typography>
      <Container maxWidth="md" >
        <RegisterUserForm />
      </Container>
    </>
  );
}
