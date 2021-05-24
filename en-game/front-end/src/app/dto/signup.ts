export enum ROLE {
  ADMIN = "ROLE_ADMIN",
  USER = "ROLE_USER",
  MODERATOR = "ROLE_MODERATOR",
}

export interface SignUp {
  name: string;
  email: string;
  password: string;
  role: ROLE;
}

export interface SignIn {
  email: string;
  password: string;
}

export interface UpdateUser {
  firstName?: string;
  lastName?: string;
  email?: string;
  password?: string;
}
