import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";

//pages
import Appointments from "../pages/Appointments";
import Login from "../pages/Login";
import Register from "../pages/Register";
import RegisterMedical from "../pages/RegisterMedical";
import CreateUpdateAppointment from "../pages/CreateUpdateAppointment";
import Medicals from "../pages/Medicals";
import MedicalScheludes from "../pages/MedicalScheludes";
import Home from "../pages/Home";
import MainLayaout from "../pages/MainLayaout";
import AccountInLayaout from "../pages/AccountInLayaout";
import { ErrorPage } from "../pages/ErrorPage";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<MainLayaout />}>
      <Route path="account" element={<AccountInLayaout />}>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
      </Route>

      <Route path="home/:id" element={<Home />} />
      <Route path="my-appointments" element={<Appointments />} />
      <Route path="medical-schedules" element={<MedicalScheludes />} />
      <Route path="medicals" element={<Medicals />} />
      <Route path="create-update-appointments" element={<CreateUpdateAppointment />} />
      <Route path="register-medical" element={<RegisterMedical />} />
      <Route path="*" element={<ErrorPage />} />
    </Route>
  )
);

export default function Router() {
  return <RouterProvider router={router} />;
}
