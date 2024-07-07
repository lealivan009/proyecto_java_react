import { Button, Grid, Paper } from '@mui/material';
import { Form, Formik } from 'formik';

import { useNavigate, useParams } from 'react-router-dom';
import { useMedical } from '../../hooks/useMedical';
import { RegisterAppointment } from '../../models/appointment.models';
import { Schedule } from '../../models/schedule.models';
import CustomInput from './CustomInput';
import { validateSchedule } from '../../utils/date-utils';
import { registerAppoinment } from '../../services/appointment.service';

export default function AppointmentForm() {
    const navigate = useNavigate();
    const {medical, getMedical} = useMedical();
    const { id } = useParams();
    getMedical(id);
    const nameDay = localStorage.getItem("nameDay");

    const schedule: Schedule = medical?.consultingDates.find(schedule => schedule.nameDay.toString() == nameDay);
    
    console.log(schedule)
    const initialValues: RegisterAppointment = {
        patient_name: '',
        userId: localStorage.getItem("userId"),
        consultingReason: '',
        consultingDate: undefined,
        medicalId: id,
        nameDay: nameDay,
    }


    const onSubmit = (values: RegisterAppointment) => {
        const consulting = new Date(values.consultingDate);
        if(validateSchedule(schedule.startTime, schedule.endTime, consulting)){
            alert("Horario invalido!");
        }else{
            registerAppoinment(values);
            navigate("/home")
        }
    }

  return (
    <Formik
    initialValues={initialValues}
    onSubmit={onSubmit}
  >
    {({ isSubmitting }) => (
      <Form>
        <Paper>
          <Grid
            container
            p={4}
            gap={3}
            direction={"column"}
            textAlign={"center"}
          >
            <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput 
                    name="medico"
                    type="text"
                    value={medical?.fullname}
                    InputProps={{
                        readOnly: true,
                      }}
                />
            </Grid>
            <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput 
                    label="Día"
                    name="nameDay"
                    type="text"
                    InputProps={{
                        readOnly: true,
                      }}
                />
            </Grid>
            <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput 
                    label="Nombre"
                    name="patient_name"
                    type="text"
                />
            </Grid>
            <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
              <CustomInput
                label="Motivo"
                name="consultingReason"
                type="multiline"
                multiline
                rows={5}
                placeholder="Motivo de la consulta"
              />
            </Grid>
            <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput 
                label="Horario"
                name="consultingDate"
                placeholder="hh:mm:ss"
                type="text"
                />
            </Grid>
            <Grid>
              <Button
                color="primary"
                variant="contained"
                disabled={isSubmitting}
                type="submit"
              >
                Registrar turno 
              </Button>
            </Grid>
          </Grid>
        </Paper>
      </Form>
    )}
  </Formik>
  );
}
