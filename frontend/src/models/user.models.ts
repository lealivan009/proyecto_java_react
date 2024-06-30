interface AbstractUser {
    email: string,
    name: string,
    lastname: string,
    dni: string,
    birthDate: Date,
    photo: string,
}

export interface User extends AbstractUser {
    id: string,
}

export interface RegisterUser extends AbstractUser {
    password: string,
    repeatPassword: string,
}

export interface UpdateUser extends AbstractUser {
    password: string,
}

export interface LoginUser {
    email: string,
    password: string
}