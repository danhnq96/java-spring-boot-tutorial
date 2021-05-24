import { ROLE } from "./signup";

export interface UserRole {
  id: number;
  name: ROLE;
}

export interface User {
  id: number;
  email: string;
  emailVerified: boolean;
  imageUrl: string;
  name: string;
  provider: string;
  providerId: string;
  roles: UserRole[];
  createdAt: string;
  updateAt: string;
}
