import {
  Alert,
  Button,
  Card,
  CardContent,
  Container,
  Snackbar,
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableRow,
  Typography,
} from "@mui/material";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { getMedicalById } from "../services/medical.service";

const MedicalSchedules = () => {
  const navigate = useNavigate();
  const { id } = useParams();
  const [medical, setMedical] = useState(null);
  const [message, setMessage] = useState({ type: "", text: "" });

  useEffect(() => {
    // Función para obtener los datos del médico desde la API
    const fetchData = async () => {
        const data = await getMedicalById(id);
        setMedical(data); // Almacenar los datos del médico en el estado
    };
    fetchData(); 
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

  //Función para manejar la solicitud de turno
  const handleRequestAppointment = async (nameDay) => {
    try {
      localStorage.setItem("nameDay", nameDay);
      navigate("../../" + id + "/create-update-appointments");
    } catch (error) {
      console.error("Error creating appointment", error);
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
                          handleRequestAppointment(schedule.nameDay)
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
