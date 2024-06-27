import { useEffect, useState } from "react";
import { getAllUser } from "./services/user.service";
import { User } from "./models/user.models";
import { UserList } from "./components/UserList";
import { Typography } from "@mui/material";

export function App() {
  const [users, setUsers] = useState<Array<User>>([]);

  useEffect(() => {
    getAllUser().then((data) => setUsers(data));
  }, []);

  return (
    <>
      <Typography variant="h1" align="center">
        Softek app
      </Typography>
      <UserList users={users} />
    </>
  );
}

export default App;
