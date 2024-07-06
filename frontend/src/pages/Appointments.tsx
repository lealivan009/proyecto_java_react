import { useEffect, useState } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
} from "@mui/material";
import { getAppointments } from "../services/user.service";
import { Appointment } from "../models/appointment.models";

const Appointments = () => {
  const [appointments, setAppointments] = useState([]);
  const userId = localStorage.getItem("userId"); // Obtener userId del localStorage

  useEffect(() => {
    const fetchAppointments = async () => {
      try {
        const response = await getAppointments(userId);
        setAppointments(response);
      } catch (error) {
        console.error("Error fetching appointments:", error);
      }
    };

    fetchAppointments();
  }, [userId]);

  return (
    <div>
      <Typography variant="h4" gutterBottom>
        Mis Turnos
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Dia de Consulta</TableCell>
              <TableCell>Fecha de Consulta</TableCell>
              <TableCell>Motivo</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {appointments != null &&
              appointments.map((appointment: Appointment) => (
                <TableRow key={appointment.id}>
                  <TableCell>{appointment.nameDay}</TableCell>
                  <TableCell>{appointment.consultingDate}</TableCell>
                  <TableCell>{appointment.consultingReason}</TableCell>
                </TableRow>
              ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default Appointments;
