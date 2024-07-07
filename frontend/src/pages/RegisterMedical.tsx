import { Container, Typography } from "@mui/material";
import RegisterMedicalForm from "../components/form/RegisterMedicalForm";

export default function RegisterMedical() {
  return (
    <Container>
      <Typography variant="h4">Registrar Médico</Typography>
      <RegisterMedicalForm />
    </Container>
  )
}
