import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { useParams } from 'react-router-dom';
import { Container, Card, CardContent, Typography, Table, TableBody, TableCell, TableHead, TableRow, Button } from '@mui/material';

const MedicalSchedules = () => {
    const { id } = useParams();
    const [medical, setMedical] = useState(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/medicals/${id}`);
                setMedical(response.data);
            } catch (error) {
                console.error('Error fetching the data', error);
            }
        };

        fetchData();
    }, [id]);

    if (!medical) {
        return <div>Loading...</div>;
    }

    // Define the order of the days
    const dayOrder = ["MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"];

    // Sort the consultingDates based on the order of the days
    const sortedConsultingDates = medical.consultingDates.sort((a, b) => {
        return dayOrder.indexOf(a.nameDay) - dayOrder.indexOf(b.nameDay);
    });

    return (
        <Container>
            <Card variant="outlined" style={{ marginTop: '20px', padding: '20px' }}>
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
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {sortedConsultingDates && sortedConsultingDates.length > 0 ? (
                                sortedConsultingDates.map((schedule) => (
                                    <TableRow key={schedule.id}>
                                        <TableCell>{schedule.nameDay}</TableCell>
                                        <TableCell>{schedule.startTime}</TableCell>
                                        <TableCell>{schedule.endTime}</TableCell>
                                    </TableRow>
                                ))
                            ) : (
                                <TableRow>
                                    <TableCell colSpan={3}>No schedules available</TableCell>
                                </TableRow>
                            )}
                        </TableBody>
                    </Table>
                    <Button variant="contained" color="primary" style={{ marginTop: '20px' }}>
                        Solicitar turno
                    </Button>
                </CardContent>
            </Card>
        </Container>
    );
};

export default MedicalSchedules;
