import {
  Route,
  RouterProvider,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";

//pages
import { Home } from "../pages/Home";
import Appointments from "../pages/Appointments";
import Login from "../pages/Login";
import Register from "../pages/Register";
import RegisterMedical from "../pages/RegisterMedical";
import CreateUpdateAppointment from "../pages/CreateUpdateAppointment";
import Medicals from "../pages/Medicals";
import MedicalScheludes from "../pages/MedicalScheludes";
import NavBar from "../pages/NavBar";

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/" element={<NavBar /> }>
      <Route path="home" element={<Home />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Register />} />
      <Route path="my-appointments" element={<Appointments />} />
      <Route path="medical-schedules" element={<MedicalScheludes />} />
      <Route path="medicals" element={<Medicals />} />
      <Route path="create-update-appointments" element={<CreateUpdateAppointment />} />
      <Route path="register-medical" element={<RegisterMedical />} />
    </Route>
  )
);

export default function Router() {
  return <RouterProvider router={router} />;
}
