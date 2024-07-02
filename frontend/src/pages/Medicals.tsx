import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { Container, Typography, List, ListItem, ListItemText, Paper, Button } from '@mui/material';

const Medicals = () => {
    const [medicals, setMedicals] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/medicals');
                setMedicals(response.data);
            } catch (error) {
                console.error('Error fetching the data', error);
            }
        };

        fetchData();
    }, []);

    const handleViewDetails = (id: string) => {
        navigate(`/details/${id}`);
    };

    return (
        <Container>
            <br />
            <Typography variant="h4" component="h1" gutterBottom>
                Medical List
            </Typography>
            <Paper elevation={3}>
                <List>
                    {medicals.map((medical) => (
                        <ListItem key={medical.id} divider>
                            <ListItemText
                                primary={medical.fullname}
                                secondary={medical.specialityType}
                            />
                            <Button
                                variant='contained'
                                color='success'
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
