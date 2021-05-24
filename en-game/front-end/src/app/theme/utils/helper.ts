import { USER_INFO } from "../constants";
import { User } from "../../dto/user";
import { ROLE } from "../../dto/signup";

export const userInfo = (): User => {
  return JSON.parse(sessionStorage.getItem(USER_INFO));
};

export const checkRole = (roles: ROLE[]): boolean => {
  const user = userInfo();

  if (user && user.roles) {
    return user.roles.some((role) => {
      return roles.includes(role.name);
    });
  }

  return false;
};
