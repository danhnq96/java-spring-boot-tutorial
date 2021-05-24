import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { APIHttpService } from "../theme/utils/api-http.service";
import { ACCESS_TOKEN, USER_INFO, STATE } from "../theme/constants";
import { User, UserRole } from "../dto/user";

@Injectable({
  providedIn: "root",
})
export class AuthService {
  private apiHttpService: APIHttpService;
  public user: BehaviorSubject<User>;

  public isLogin: BehaviorSubject<boolean>;

  public roleAs: BehaviorSubject<[]>;

  constructor(public http: HttpClient, public snackBar: MatSnackBar, public router: Router) {
    this.apiHttpService = new APIHttpService(http);
    this.user = new BehaviorSubject<User>(null);
    this.isLogin = new BehaviorSubject<boolean>(false);
  }

  public signup(url: string, data: any): Observable<any> {
    return this.apiHttpService.post(url, data);
  }

  public login(url: string, data: any): void {
    this.apiHttpService.post(url, data).subscribe(
      (res: any) => {
        this.snackBar.open("You login successfully!", "×", {
          panelClass: "success",
          verticalPosition: "top",
          duration: 3000,
        });
        localStorage.setItem(ACCESS_TOKEN, res.accessToken);
        this.isLogin.next(true);

        this.getUser("user/me", true);
      },
      (error) => {
        this.snackBar.open(error.message, "×", {
          panelClass: "error",
          verticalPosition: "top",
          duration: 3000,
        });
      },
    );
  }

  public socialLogin(token: string) {
    localStorage.setItem(ACCESS_TOKEN, token);
    this.isLogin.next(true);

    this.getUser("user/me", true);
  }

  public updateUserInfo(url: string, data: any): void {
    this.apiHttpService.put(url, data).subscribe(
      (res: any) => {
        this.snackBar.open("Your information was updated successfully!", "×", {
          panelClass: "success",
          verticalPosition: "top",
          duration: 3000,
        });

        this.user.next(res);
      },
      (error) => {
        this.snackBar.open(error.message, "×", {
          panelClass: "error",
          verticalPosition: "top",
          duration: 3000,
        });
      },
    );
  }

  public async getUser(url: string, isLogin?: boolean): Promise<void> {
    const currentUrl = window.location.href;
    await this.apiHttpService.get(url).subscribe((res: any) => {
      this.user.next(res);
      this.roleAs = res.roles;
      this.isLogin.next(true);

      if (isLogin) {
        this.router.navigate(["/account"]);
      } else {
        console.log("Cai quai gi day: ", currentUrl);
      }
    });
  }

  public logout(): void {
    this.isLogin.next(false);
    this.roleAs = null;
    this.user.next(null);

    localStorage.removeItem(ACCESS_TOKEN);
    this.router.navigate(["/"]);
  }

  public isLoggedIn(): boolean {
    return this.isLogin.getValue();
  }

  public getRole(): UserRole[] {
    const user = this.user.getValue();

    return user ? user.roles : [];
  }

  public checkPermission(roles: string[]): boolean {
    const userRoles = this.getRole();
    // console.log("checkPermission =====", userRoles);
    return userRoles.some((role) => roles.includes(role.name));
  }
}
