import { Box, Container } from "@mui/material";
import { Outlet } from "react-router-dom";
import NavBar from "../components/NavBar";

export default function MainLayaout() {
  return (
    <Box sx={{ display: "flex" }}>
    <NavBar />
    <Container
      sx={{
        display: "flex",
        justifyContent: "center",
        flexDirection: "column",
        marginTop: "8vh",
      }}
    >
      <Outlet />
    </Container>
    </Box>
  );
}
