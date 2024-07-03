import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  Container,
  Typography,
  List,
  ListItem,
  ListItemText,
  Paper,
  Button,
  TextField,
  MenuItem,
} from "@mui/material";
import { getMedicals } from "../services/medical.service";

const Medicals = () => {
  // Estados para almacenar los datos de los médicos y especialidades
  const [medicals, setMedicals] = useState([]); // Todos los médicos obtenidos de la API
  const [filteredMedicals, setFilteredMedicals] = useState([]); // Médicos filtrados según los criterios de búsqueda
  const [specialities, setSpecialities] = useState([]); // Lista de especialidades únicas para el filtro
  const [selectedSpeciality, setSelectedSpeciality] = useState(""); // Especialidad seleccionada en el filtro
  const [doctorName, setDoctorName] = useState(""); // Nombre del doctor para filtrar
  const navigate = useNavigate(); // Hook de React Router para la navegación

  // Obtener la lista de médicos y especialidades al cargar el componente
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getMedicals();
        setMedicals(response.data); // Almacenar todos los médicos obtenidos
        setFilteredMedicals(response.data); // Almacenar todos los médicos como inicialmente filtrados

        // Extraer las especialidades únicas para el filtro desplegable
        const uniqueSpecialities = [
          ...new Set(response.data.map((medical) => medical.specialityType)),
        ];
        setSpecialities(uniqueSpecialities); // Almacenar las especialidades únicas
      } catch (error) {
        console.error("Error fetching the data", error);
      }
    };

    fetchData(); // Llamar a la función para obtener datos al montar el componente
  }, []); // Se ejecuta solo una vez al cargar el componente, debido al arreglo vacío []

  // Filtrar la lista de médicos cuando cambia la especialidad seleccionada o el nombre del doctor
  useEffect(() => {
    filterMedicals();
  }, [selectedSpeciality, doctorName]);

  // Función para filtrar los médicos según los criterios seleccionados
  const filterMedicals = () => {
    let filtered = medicals; // Iniciar con todos los médicos
    if (selectedSpeciality) {
      filtered = filtered.filter(
        (medical) => medical.specialityType === selectedSpeciality
      ); // Filtrar por especialidad seleccionada
    }
    if (doctorName) {
      filtered = filtered.filter((medical) =>
        medical.fullname.toLowerCase().includes(doctorName.toLowerCase())
      ); // Filtrar por nombre del doctor (insensible a mayúsculas/minúsculas)
    }
    setFilteredMedicals(filtered); // Actualizar la lista de médicos filtrados
  };

  // Función para manejar la navegación al detalle de un médico
  const handleViewDetails = (id) => {
    navigate(`/details/${id}`); // Redirigir a la ruta de detalles con el ID del médico
  };

  return (
    <Container>
      <br />
      <Typography variant="h4" component="h1" gutterBottom>
        Medical List
      </Typography>
      {/* Campo de texto para filtrar por especialidad */}
      <TextField
        label="Filter by Speciality"
        select
        value={selectedSpeciality}
        onChange={(e) => setSelectedSpeciality(e.target.value)}
        fullWidth
        margin="normal"
      >
        {specialities.map((speciality) => (
          <MenuItem key={speciality} value={speciality}>
            {speciality}
          </MenuItem>
        ))}
      </TextField>
      {/* Campo de texto para filtrar por nombre del doctor */}
      <TextField
        label="Filter by Doctor Name"
        value={doctorName}
        onChange={(e) => setDoctorName(e.target.value)}
        fullWidth
        margin="normal"
      />
      {/* Lista de médicos filtrados */}
      <Paper elevation={3}>
        <List>
          {filteredMedicals.map((medical) => (
            <ListItem key={medical.id} divider>
              <ListItemText
                primary={medical.fullname}
                secondary={medical.specialityType}
              />
              {/* Botón para ver detalles del médico */}
              <Button
                variant="contained"
                color="success"
                onClick={() => handleViewDetails(medical.id)}
              >
                Ver detalle
              </Button>
            </ListItem>
          ))}
        </List>
      </Paper>
    </Container>
  );
};

export default Medicals;
