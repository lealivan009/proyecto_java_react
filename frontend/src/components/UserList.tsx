import {
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";

interface Props {
  users: Array<{
    id: string;
    email: string;
    name: string;
    lastname: string;
  }>;
}

export function UserList({ users }: Props) {
  return (
    <>
      <TableContainer>
        <TableHead>
          <TableRow>
            <TableCell align="center">id</TableCell>
            <TableCell align="center">email</TableCell>
            <TableCell align="center">name</TableCell>
            <TableCell align="center">lastname</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {users.map((user) => (
            <TableRow key={user.id}>
              <TableCell>{user.id}</TableCell>
              <TableCell>{user.email}</TableCell>
              <TableCell>{user.name}</TableCell>
              <TableCell>{user.lastname}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </TableContainer>
    </>
  );
}
