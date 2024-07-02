import {
  Box,
  Container,
  ToggleButton,
  ToggleButtonGroup,
  Typography,
} from "@mui/material";
import { useState } from "react";
import { Link, Outlet } from "react-router-dom";

export default function AccountInLayaout() {
    const [alignment, setAlignment] = useState('ingresar');

    const handleChange = (
      event: React.MouseEvent<HTMLElement>,
      newAlignment: string,
    ) => {
      setAlignment(newAlignment);
    };

  return (
    <Container>
      <Typography p={10} textAlign={"center"} variant="h2">
        AlMedin
      </Typography>
      <Typography textAlign={"center"} variant="subtitle1">
        Tu aliado en salud y bienestar social
      </Typography>
      <Typography textAlign={"center"} variant="body2">
        En AlMedin, nos dedicamos a ofrecer soluciones integrales en obras sociales para garantizar tu bienestar y el de tu familia. Nuestro compromiso es brindarte acceso a servicios de salud de alta calidad, con una atención personalizada y un enfoque en la prevención y el cuidado continuo. Confía en nosotros para cuidar de lo más valioso: tu salud.
      </Typography>
      <Box margin={5} display={"flex"} justifyContent={"center"} gap={10}>
        <ToggleButtonGroup
          color="primary"
          value={alignment}
          onChange={handleChange}
          exclusive
        >
          <Link to={"login"}>
            <ToggleButton value={"ingresar"}>Ingresar</ToggleButton>
          </Link>
          <Link to={"register"}>
            <ToggleButton value={"registrar"}>Registrarme</ToggleButton>
          </Link>
        </ToggleButtonGroup>
      </Box>

      <Outlet />
      
    </Container>
  );
}

