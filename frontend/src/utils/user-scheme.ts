import * as yup from "yup";

const passwordRegex = /^[a-zA-Z0-9]+$/;
const nameLastnameRegex = /^[a-zA-Z]+$/;

export const registerUserScheme = yup.object().shape({
    name: 
        yup.string()
        .matches(nameLastnameRegex, "El nombre no es valido")
        .required("El nombre es requerido"),
    lastname: 
        yup.string()
    .matches(nameLastnameRegex, "El apellido no es valido")
    .required("El apellido es requerido"),
    dni: yup.string().min(7, "Dni no valido").required("El dni es requerido"),
    email: 
        yup.string().email("Por favor ingrese un email valido")
        .required("El mail es requerido"),
    password: 
        yup.string()
        .min(8, "Minimo 8 caracteres")
        .matches(passwordRegex, "Por favor ingrese una contraseña valida")
        .required("La contraseña es requerida"),
    repeatPassword: 
        yup.string()
        .oneOf([yup.ref('password')], "La contraseña no coincide")
})

export const loginUserScheme = yup.object().shape({
    email: 
    yup.string().email("Por favor ingrese un email valido")
    .required("El mail es requerido"),
password: 
    yup.string()
    .min(8, "Minimo 8 caracteres")
    .matches(passwordRegex, "Por favor ingrese una contraseña valida")
    .required("La contraseña es requerida"),
})