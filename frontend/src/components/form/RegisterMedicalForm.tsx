import { Button, Grid, MenuItem, Paper } from "@mui/material";
import { Form, Formik } from "formik";
import { useState } from "react";
import { SpecialityEnum } from "../../models/enums/speciality.models";
import { registerMedicals } from "../../services/medical.service";
import { validateTimeFormat } from "../../utils/date-utils";
import CustomInput from "./CustomInput";
import CustomSelect from "./CustomSelect";
import { RegisterMedical } from "../../models/medical.models";

export default function RegisterMedicalForm() {
  const [speciality, setSpeciality] = useState<SpecialityEnum>(SpecialityEnum.CARDIOLOGY);

  const initialValues: RegisterMedical = {
    startTime: undefined,
    endTime: undefined,
    fullname: "",
    matricule: "",
    consultingPlace: "",
    medicalSpeciality: speciality,
  };

  const handleChange = (event) => {
    setSpeciality(event.target.value);
  };

  const onSubmit = (values) => {
    if(!validateTimeFormat(values.startTime) && !validateTimeFormat(values.endTime)){
        alert("Formato de horas incompatibles")
    }else{
        registerMedicals(values).then(res => console.log(res))
    }
  };

  return (
    <Formik 
        initialValues={initialValues}
        onSubmit={onSubmit}>
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
                  label="Nombre Completo"
                  name="fullname"
                  type="text"
                />
                <CustomInput label="Matricula" name="matricule" type="text" />
              </Grid>
              <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput
                  label="Lugar de consultas"
                  name="consultingPlace"
                  type="text"
                />
                <CustomSelect
                  label="Especialidades"
                  name="specialityType"
                  value={speciality}
                  onChange={handleChange}
                >
                    {/* queria hacer un map pero no pude xd */}
                  <MenuItem value={SpecialityEnum.CARDIOLOGY}>
                    {SpecialityEnum.CARDIOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.ANESTHESIOLOGY}>
                    {SpecialityEnum.ANESTHESIOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.GENERAL_SURGERY}>
                    {SpecialityEnum.GENERAL_SURGERY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.PLASTIC_SURGERY}>
                    {SpecialityEnum.PLASTIC_SURGERY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.DERMATOLOGY}>
                    {SpecialityEnum.DERMATOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.ENDOCRINOLOGY}>
                    {SpecialityEnum.ENDOCRINOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.GASTROENTEROLOGY}>
                    {SpecialityEnum.GASTROENTEROLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.GERIATRICS}>
                    {SpecialityEnum.GERIATRICS}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.GYNECOLOGY_AND_OBSTETRICS}>
                    {SpecialityEnum.GYNECOLOGY_AND_OBSTETRICS}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.HEMATOLOGY}>
                    {SpecialityEnum.HEMATOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.INFECTIOUS_DISEASE}>
                    {SpecialityEnum.INFECTIOUS_DISEASE}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.FAMILY_AND_GENERAL_MEDICINE}>
                    {SpecialityEnum.FAMILY_AND_GENERAL_MEDICINE}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.INTERNAL_MEDICINE}>
                    {SpecialityEnum.INTERNAL_MEDICINE}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.NEPHROLOGY}>
                    {SpecialityEnum.NEPHROLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.PULMONOLOGY}>
                    {SpecialityEnum.PULMONOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.NEUROSURGERY}>
                    {SpecialityEnum.NEUROSURGERY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.NEUROLOGY}>
                    {SpecialityEnum.NEUROLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.NUTRITION}>
                    {SpecialityEnum.NUTRITION}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.OPHTHALMOLOGY}>
                    {SpecialityEnum.OPHTHALMOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.ONCOLOGY}>
                    {SpecialityEnum.ONCOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.ORTHOPEDICS_AND_TRAUMATOLOGY}>
                    {SpecialityEnum.ORTHOPEDICS_AND_TRAUMATOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.OTORHINOLARYNGOLOGY}>
                    {SpecialityEnum.OTORHINOLARYNGOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.PEDIATRICS}>
                    {SpecialityEnum.PEDIATRICS}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.PSYCHIATRY}>
                    {SpecialityEnum.PSYCHIATRY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.RADIOLOGY}>
                    {SpecialityEnum.RADIOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.RHEUMATOLOGY}>
                    {SpecialityEnum.RHEUMATOLOGY}
                  </MenuItem>
                  <MenuItem value={SpecialityEnum.UROLOGY}>
                    {SpecialityEnum.UROLOGY}
                  </MenuItem>
                </CustomSelect>
              </Grid>
              <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput
                  label="Inicio de consultas"
                  name="startTime"
                  placeholder="hh:mm"
                  type="text"
                />
                <CustomInput
                  label="Fin de consultas"
                  name="endTime"
                  placeholder="hh:mm"
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
                  Registrar medico
                </Button>
              </Grid>
            </Grid>
          </Paper>
        </Form>
      )}
    </Formik>
  );
}
