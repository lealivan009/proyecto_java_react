import { Button,  Grid, Paper } from "@mui/material";
import { LoginUser } from "../../models/user.models";
import CustomInput from "./CustomInput";
import { Form, Formik } from "formik";
import { loginUserScheme } from "../../utils/scheme";
import { loginUser } from "../../services/user.service";
import { Navigate } from "react-router-dom";
import { useState } from "react";

export default function LoginUserForm() {
  const [id, setId] = useState<string>();

  const initialValues: LoginUser = {
    email: "",
    password: "",
  };

  const onSubmit = async (values: LoginUser)=>{
      const { id } = await loginUser(values);
      setId(id);
      localStorage.setItem("userId", id);
    }
  
  if(id != null) return <Navigate to={"../../home"} />
  
  return (
    <Formik
      initialValues={initialValues}
      validationSchema={loginUserScheme}
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
                  placeholder="Ingrese su contraseña"
                />
              </Grid>
              <Grid>
                <Button
                  color="primary"
                  variant="contained"
                  disabled={isSubmitting}
                  type="submit"
                >
                  ingresar 
                </Button>
              </Grid>
            </Grid>
          </Paper>
        </Form>
      )}
    </Formik>
  );
}
