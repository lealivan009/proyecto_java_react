import { Form, Formik } from "formik";
import { RegisterUser } from "../../models/user.models";
import { registerUserScheme } from "../../utils/scheme";
import { Button, Grid, Paper, Typography } from "@mui/material";
import CustomInput from "./CustomInput";
import { useState } from "react";
import { registerUser } from "../../services/user.service";
import { dateFormat } from "../../utils/date-formatter";

export default function CustomForm() {
  const [sendForm, setSendForm] = useState(false);

  const initialValues: RegisterUser = {
    name: "",
    lastname: "",
    dni: "",
    email: "",
    password: "",
    repeatPassword: "",
    birthDate: new Date(),
    photo: "",
  };

  const onSubmit = async (values, actions): Promise<any> => {
    try {
      values.birthDate = dateFormat(new Date("1999-10-27"));
      const response = await registerUser(values);
      console.log(response.status);
      actions.resetForm();
      setSendForm(!sendForm);
    } catch (error) {
      console.log(error.response.data);
    }
  };

  return (
    <Formik
      initialValues={initialValues}
      validationSchema={registerUserScheme}
      onSubmit={onSubmit}
    >
      {({ isSubmitting }) => (
        <Form>
          <Paper>
            <Grid container p={4} gap={3} direction={"column"} textAlign={"center"} >
              <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput
                  label="Nombre"
                  name="name"
                  type="text"
                  placeholder="Ingrese su nombre"
                />
                <CustomInput
                  label="Apellido"
                  name="lastname"
                  type="text"
                  placeholder="Ingrese su apellido"
                />
              </Grid>
              <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
              <CustomInput
                  label="DNI"
                  name="dni"
                  type="text"
                  placeholder="Ingrese su dni"
                />
                <CustomInput
                  label="Email"
                  name="email"
                  type="email"
                  placeholder="Ingrese un email"
                />
              </Grid>
              <Grid xs={12} justifyContent={"space-evenly"} display={"flex"}>
                <CustomInput
                  label="Contraseña"
                  name="password"
                  type="password"
                  placeholder="Ingrese una contraseña"
                />
                <CustomInput
                  label="Contraseña"
                  name="repeatPassword"
                  type="password"
                  placeholder="Repita la contraseña"
                />
              </Grid>
              <Grid p={2} >
                <Button
                  color="primary"
                  variant="contained"
                  disabled={isSubmitting}
                  type="submit"
                >
                  Registrar
                </Button>
                {sendForm && (
                  <Typography color={"green"}>Te has registrado!</Typography>
                )}
              </Grid>
            </Grid>
          </Paper>
        </Form>
      )}
    </Formik>
  );
}
