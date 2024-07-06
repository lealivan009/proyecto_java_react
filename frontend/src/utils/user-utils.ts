import { User } from "../models/user.models";

export const initialsLetters = (user: User| null | undefined) => {
    const letters = user?.name[0].concat(user?.lastname[0]);
    return letters?.toUpperCase();
} 