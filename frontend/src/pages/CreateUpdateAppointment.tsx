import * as React from 'react';
import { useState } from 'react';
import { TextField, Button, Box, FormControl, InputLabel, MenuItem, Select, AppBar, Toolbar, Typography, CssBaseline, IconButton } from '@mui/material';
import axios from 'axios';
import { Link, Outlet } from 'react-router-dom';

const BASE_URL = 'http://localhost:8080/api';

const SpecialistSelector: React.FC<{ specialist: string, setSpecialist: (specialist: string) => void }> = ({ specialist, setSpecialist }) => {
  return (
    <FormControl fullWidth margin="normal">
      <InputLabel>Especialista</InputLabel>
      <Select
        value={specialist}
        onChange={(e) => setSpecialist(e.target.value as string)}
      >
        <MenuItem value="cardiologo">Cardiólogo</MenuItem>
        <MenuItem value="dermatologo">Dermatólogo</MenuItem>
        <MenuItem value="neurologo">Neurólogo</MenuItem>
        {}
      </Select>
    </FormControl>
  );
};

const DatePicker: React.FC<{ date: string, setDate: (date: string) => void }> = ({ date, setDate }) => {
  return (
    <TextField
      label="Fecha"
      type="date"
      fullWidth
      margin="normal"
      InputLabelProps={{
        shrink: true,
      }}
      value={date}
      onChange={(e) => setDate(e.target.value)}
    />
  );
};

const TimePicker: React.FC<{ time: string, setTime: (time: string) => void }> = ({ time, setTime }) => {
  return (
    <TextField
      label="Hora"
      type="time"
      fullWidth
      margin="normal"
      InputLabelProps={{
        shrink: true,
      }}
      value={time}
      onChange={(e) => setTime(e.target.value)}
    />
  );
};

const CreateUpdateAppointment: React.FC = () => {
  const [name, setName] = useState('');
  const [reason, setReason] = useState('');
  const [specialist, setSpecialist] = useState('');
  const [date, setDate] = useState('');
  const [time, setTime] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    const appointment = { name, reason, specialist, date, time };
    try {
      const response = await axios.post(`${BASE_URL}/appointments`, appointment);
      console.log('Turno creado:', response.data);
    } catch (error) {
      console.error('Error al crear el turno:', error);
    }
  };

  const [mobileOpen, setMobileOpen] = useState(false);

  const handleDrawerToggle = () => {
    setMobileOpen((prevState) => !prevState);
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar component="nav">
        <Toolbar>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            edge="start"
            onClick={handleDrawerToggle}
            sx={{ mr: 2, display: { sm: 'none' } }}
          ></IconButton>
          <Typography
            variant="h6"
            component="div"
            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
          >
            AlMedin
          </Typography>
          <Box sx={{ display: { xs: 'none', sm: 'block' } }}>
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
      <Box component="main" sx={{ p: 3 }}>
        <Toolbar />
        <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          Crear/Modificar Turno
        </Typography>
        <Box component="form" onSubmit={handleSubmit} sx={{ mt: 3 }}>
          <TextField
            label="Nombre"
            fullWidth
            margin="normal"
            value={name}
            onChange={(e) => setName(e.target.value)}
            required
          />
          <TextField
            label="Razón de la consulta"
            fullWidth
            margin="normal"
            value={reason}
            onChange={(e) => setReason(e.target.value)}
            required
          />
          <SpecialistSelector specialist={specialist} setSpecialist={setSpecialist} />
          <DatePicker date={date} setDate={setDate} />
          <TimePicker time={time} setTime={setTime} />
          <Button type="submit" variant="contained" color="primary" fullWidth sx={{ mt: 2 }}>
            Reservar Turno
          </Button>
        </Box>
      </Box>
    </Box>
  );
};

export default CreateUpdateAppointment;
