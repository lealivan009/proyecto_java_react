import { useEffect, useState } from "react";
import axios from "axios";
import { useParams } from "react-router-dom";
import {
  Container,
  Card,
  CardContent,
  Typography,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Button,
  Snackbar,
  Alert,
} from "@mui/material";
import { getMedicalById } from "../services/medical.service";
import { registerAppoinment } from "../services/appointment.service";
import { RegisterAppointment } from "../models/appointment.models";

const MedicalSchedules = () => {
  // Obtener el parámetro 'id' de la URL
  const { id } = useParams();
  // Estado para almacenar la información del médico
  const [medical, setMedical] = useState(null);
  // Estado para almacenar el mensaje de éxito o error
  const [message, setMessage] = useState({ type: "", text: "" });

  useEffect(() => {
    // Función para obtener los datos del médico desde la API
    const fetchData = async () => {
      try {
        const data = await getMedicalById(id);
        setMedical(data); // Almacenar los datos del médico en el estado
      } catch (error) {
        console.error("Error fetching the data", error);
      }
    };

    fetchData(); // Llamar a la función para obtener los datos
  }, [id]); // Ejecutar el efecto cuando cambia el 'id'

  // Mostrar un mensaje de carga mientras se obtienen los datos
  if (!medical) {
    return <div>Loading...</div>;
  }

  // Definir el orden de los días
  const dayOrder = [
    "MONDAY",
    "TUESDAY",
    "WEDNESDAY",
    "THURSDAY",
    "FRIDAY",
    "SATURDAY",
    "SUNDAY",
  ];

  // Ordenar las fechas de consulta según el orden de los días
  const sortedConsultingDates = medical.consultingDates.sort((a, b) => {
    return dayOrder.indexOf(a.nameDay) - dayOrder.indexOf(b.nameDay);
  });

  // Función para manejar la solicitud de turno
  const handleRequestAppointment = async (startTime) => {
    console.log(startTime);
    // Datos del turno a enviar en la petición POST
    const appointmentData: RegisterAppointment = {
      patient_name: "Ivan",
      consultingReason: "Dolores de pecho",
      consultingDate: new Date("09:00:00.123456789"), // Aquí puedes ajustar el formato de la fecha/hora si es necesario
      medicalId: id,
      userId: localStorage.getItem("userId"),
    };

    try {
      // Hacer la petición POST para crear el turno
      const data = await registerAppoinment(appointmentData);
      console.log("Appointment created:", data);
      // Mostrar mensaje de éxito
      setMessage({ type: "success", text: "Turno creado exitosamente" });
    } catch (error) {
      console.error("Error creating appointment", error);
      // Mostrar mensaje de error
      setMessage({
        type: "error",
        text: "Hubo un error y no se pudo crear el turno",
      });
    }
  };

  return (
    <Container>
      <Card variant="outlined" style={{ marginTop: "20px", padding: "20px" }}>
        <CardContent>
          <Typography variant="h4" component="h2" gutterBottom>
            {medical.fullname}
          </Typography>
          <Typography variant="h6" component="h3" gutterBottom>
            Speciality: {medical.specialityType}
          </Typography>
          <Typography variant="h6" component="h3" gutterBottom>
            Matricule: {medical.matricule}
          </Typography>
          <Typography variant="h6" component="h3" gutterBottom>
            Consulting Place: {medical.consultingPlace}
          </Typography>
          <Typography variant="h6" component="h3" gutterBottom>
            Schedules:
          </Typography>
          <Table>
            <TableHead>
              <TableRow>
                <TableCell>Day</TableCell>
                <TableCell>Start Time</TableCell>
                <TableCell>End Time</TableCell>
                <TableCell>Action</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {sortedConsultingDates && sortedConsultingDates.length > 0 ? (
                sortedConsultingDates.map((schedule) => (
                  <TableRow key={schedule.id}>
                    <TableCell>{schedule.nameDay}</TableCell>
                    <TableCell>{schedule.startTime}</TableCell>
                    <TableCell>{schedule.endTime}</TableCell>
                    <TableCell>
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={() =>
                          handleRequestAppointment(schedule.startTime)
                        }
                      >
                        Solicitar turno
                      </Button>
                    </TableCell>
                  </TableRow>
                ))
              ) : (
                <TableRow>
                  <TableCell colSpan={4}>No schedules available</TableCell>
                </TableRow>
              )}
            </TableBody>
          </Table>
        </CardContent>
      </Card>
      <Snackbar
        open={!!message.text}
        autoHideDuration={6000}
        onClose={() => setMessage({ type: "", text: "" })}
      >
        <Alert
          onClose={() => setMessage({ type: "", text: "" })}
          severity={message.type}
          sx={{ width: "100%" }}
        >
          {message.text}
        </Alert>
      </Snackbar>
    </Container>
  );
};

export default MedicalSchedules;
