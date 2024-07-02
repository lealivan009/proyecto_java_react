import axios from "axios";
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

const Medicals = () => {
  const [medicals, setMedicals] = useState([]);
  const [filteredMedicals, setFilteredMedicals] = useState([]);
  const [specialities, setSpecialities] = useState([]);
  const [selectedSpeciality, setSelectedSpeciality] = useState("");
  const [doctorName, setDoctorName] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/medicals");
        setMedicals(response.data);
        setFilteredMedicals(response.data);

        // Extract unique specialities for filter dropdown
        const uniqueSpecialities = [
          ...new Set(response.data.map((medical) => medical.specialityType)),
        ];
        setSpecialities(uniqueSpecialities);
      } catch (error) {
        console.error("Error fetching the data", error);
      }
    };

    fetchData();
  }, []);

  useEffect(() => {
    filterMedicals();
  }, [selectedSpeciality, doctorName]);

  const filterMedicals = () => {
    let filtered = medicals;
    if (selectedSpeciality) {
      filtered = filtered.filter(
        (medical) => medical.specialityType === selectedSpeciality
      );
    }
    if (doctorName) {
      filtered = filtered.filter((medical) =>
        medical.fullname.toLowerCase().includes(doctorName.toLowerCase())
      );
    }
    setFilteredMedicals(filtered);
  };

  const handleViewDetails = (id) => {
    navigate(`/details/${id}`);
  };

  return (
    <Container>
      <br />
      <Typography variant="h4" component="h1" gutterBottom>
        Medical List
      </Typography>
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
      <TextField
        label="Filter by Doctor Name"
        value={doctorName}
        onChange={(e) => setDoctorName(e.target.value)}
        fullWidth
        margin="normal"
      />
      <Paper elevation={3}>
        <List>
          {filteredMedicals.map((medical) => (
            <ListItem key={medical.id} divider>
              <ListItemText
                primary={medical.fullname}
                secondary={medical.specialityType}
              />
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
