import InputLabel from "@mui/material/InputLabel";
import FormControl from "@mui/material/FormControl";
import { useFormik } from "formik";
import { Button, Grid, Input } from "@mui/material";
import { registerUserScheme } from "../utils/scheme";

export default function Form() {

  const { values, isSubmitting, handleChange, handleSubmit } = useFormik({
    initialValues:{
      name: "",
      lastname: "",
      dni: "",
      email: "",
      password: "",
      repeatPassword: "",
      birthDate: "",
      photo: "",
    },
    validationSchema: registerUserScheme,
    onSubmit: async (values, actions) => {
      console.log({values})
      await new Promise((resolve)=> setTimeout(resolve, 1000));
      actions.resetForm();
    },
  });

  return (
    <form onSubmit={handleSubmit} >
      <Grid container display={"flex"} direction={"column"} gap={3} >
        <Grid display={"flex"} gap={2} >
          <FormControl>
            <InputLabel>Nombre</InputLabel>
            <Input
              placeholder="Tito"
              type="text"
              id="name"
              value={values.name}
              onChange={handleChange}
            />
          </FormControl>
          <FormControl>
            <InputLabel>Apellido</InputLabel>
            <Input
              placeholder="Calderon"
              type="text"
              id="lastname"
              value={values.lastname}
              onChange={handleChange}
            />
          </FormControl>
          <FormControl>
            <InputLabel>Dni</InputLabel>
            <Input
              placeholder="14159265"
              type="text"
              id="dni"
              value={values.dni}
              onChange={handleChange}
            />
          </FormControl>
        </Grid>
        <Grid display={"flex"} gap={2} >
          <FormControl>
            <InputLabel>Email</InputLabel>
            <Input
              placeholder="email@example.com"
              type="email"
              id="email"
              value={values.email}
              onChange={handleChange}
            />
          </FormControl>
          <FormControl variant="outlined">
            <InputLabel>Contraseña</InputLabel>
            <Input
              placeholder="Contraseña"
              type="password"
              id="password"
              value={values.password}
              onChange={handleChange}
            />
          </FormControl>
          <FormControl variant="outlined">
            <InputLabel>Repita la contraseña</InputLabel>
            <Input
              placeholder="Contraseña"
              type="password"
              id="repeatPassword"
              value={values.repeatPassword}
              onChange={handleChange}
            />
          </FormControl>
        </Grid>
        <Grid display={"flex"} justifyContent={"center"}>
          <Button disabled={isSubmitting} type="submit">Registrar</Button>
        </Grid>
      </Grid>
    </form>
  );
}
