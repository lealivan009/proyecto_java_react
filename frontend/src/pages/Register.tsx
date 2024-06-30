import { Container, Typography } from "@mui/material";
import CustomForm from "../components/form/CustomForm";

export default function Register() {
  return (
    <>
      <Typography margin={3} variant="h5" textAlign={"center"}>
        Registrate como usuario
      </Typography>
      <Container maxWidth="md" >
        <CustomForm />
      </Container>
    </>
  );
}
