import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import IconButton from "@mui/material/IconButton";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import { Link, Outlet } from "react-router-dom";
import NavBarMovile from "../components/NavBarMovile";



// const navItems = ["Home", "Carta de especialistas", "Mis turnos"];

export default function NavBar() {
  const [mobileOpen, setMobileOpen] = React.useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen((prevState) => !prevState);
  };


  return (
    <Box sx={{ display: "flex" }}>
      <CssBaseline />
      <AppBar component="nav">
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: "none" } }}
          ></IconButton>
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, display: { xs: "none", sm: "block" } }}
          >
            AlMedin
          </Typography>
          <Box sx={{ display: { xs: "none", sm: "block" } }}>
            <Link to={"home"}>
              <Button sx={{ color: "#fff" }}>Home</Button>
            </Link>
            <Link to={"medicals"}>
              <Button sx={{ color: "#fff" }}>Carta especialistas</Button>
            </Link>
            <Link to={"my-appointments"}>
              <Button sx={{ color: "#fff" }}>Mis turnos</Button>
            </Link>
          </Box>
        </Toolbar>
      </AppBar>
      <NavBarMovile />
      <Box component="main" pt={30}>
        <Outlet />
      </Box>
    </Box>
  );
}
