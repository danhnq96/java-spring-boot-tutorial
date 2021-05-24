import { Injectable } from "@angular/core";
import { AccountBasicInfoDTO } from "../dto/Account/AccountBasicInfoDTO";

const TOKEN_KEY = "AuthToken";
const USERNAME_KEY = "AuthUsername";
const AUTHORITIES_KEY = "AuthAuthorities";
const ACCOUNT_BASIC_INFO_KEY = "AccountBasicInfo";
@Injectable({
  providedIn: "root",
})
export class TokenStorageService {
  private roles: Array<string> = [];
  constructor() {}

  public logOut(): void {
    window.sessionStorage.clear();
  }

  public saveAccountBasicInfo(emp: AccountBasicInfoDTO) {
    window.sessionStorage.removeItem(ACCOUNT_BASIC_INFO_KEY);
    window.sessionStorage.setItem(ACCOUNT_BASIC_INFO_KEY, JSON.stringify(emp));
  }

  public getAccountBasicInfo(): AccountBasicInfoDTO {
    return JSON.parse(sessionStorage.getItem(ACCOUNT_BASIC_INFO_KEY));
  }
  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUsername(username: string) {
    window.sessionStorage.removeItem(USERNAME_KEY);
    window.sessionStorage.setItem(USERNAME_KEY, username);
  }

  public getUsername(): string {
    return sessionStorage.getItem(USERNAME_KEY);
  }

  public saveAuthorities(authorities: string[]) {
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
  }

  public getAuthorities(): string[] {
    this.roles = [];
    if (sessionStorage.getItem(TOKEN_KEY)) {
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)).forEach((authority) => {
        this.roles.push(authority.authority);
      });
    }
    return this.roles;
  }
}
