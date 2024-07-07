import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";

//pages
import AccountInLayaout from "../pages/AccountInLayaout";
import Appointments from "../pages/Appointments";
import CreateUpdateAppointment from "../pages/CreateUpdateAppointment";
import { ErrorPage } from "../pages/ErrorPage";
import Home from "../pages/Home";
import Login from "../pages/Login";
import MainLayaout from "../pages/MainLayaout";
import Medicals from "../pages/Medicals";
import { default as MedicalSchedules, default as MedicalScheludes } from "../pages/MedicalScheludes";
import Register from "../pages/Register";
import RegisterMedical from "../pages/RegisterMedical";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<MainLayaout />}>
      <Route path="account" element={<AccountInLayaout />}>
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Register />} />
      </Route>

      <Route path="home" element={<Home />} />
      <Route path="my-appointments" element={<Appointments />} />
      <Route path="medical-schedules" element={<MedicalScheludes />} />
      <Route path="medicals" element={<Medicals />} />
      <Route path="/details/:id" element={<MedicalSchedules />} />
      <Route path=":id/create-update-appointments" element={<CreateUpdateAppointment />} />
      <Route path="/register-medical" element={<RegisterMedical />} />
      <Route path="*" element={<ErrorPage />} />
    </Route>
  )
);

export default function Router() {
  return <RouterProvider router={router} />;
}
