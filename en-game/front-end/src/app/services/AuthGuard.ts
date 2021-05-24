import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateChild,
  CanDeactivate,
  CanLoad,
  Route,
  Router,
  RouterStateSnapshot,
  UrlSegment,
  UrlTree,
} from "@angular/router";
import { AuthService } from "./AuthService";

@Injectable({
  providedIn: "root",
})
export class AuthGuard implements CanActivate, CanActivateChild, CanDeactivate<unknown>, CanLoad {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    let url: string = state.url;
    return this.checkPermission(next, url);
  }
  canActivateChild(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.canActivate(next, state);
  }
  canDeactivate(
    component: unknown,
    currentRoute: ActivatedRouteSnapshot,
    currentState: RouterStateSnapshot,
    nextState?: RouterStateSnapshot,
  ): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return true;
  }
  canLoad(route: Route, segments: UrlSegment[]): Observable<boolean> | Promise<boolean> | boolean {
    return true;
  }

  checkPermission(route: ActivatedRouteSnapshot, url: any): boolean {
    console.log("Check permission AuthGuard ======", this.authService.isLoggedIn(), url);
    if (this.authService.isLoggedIn()) {
      // this.authService.getUser("user/me", true);

      if (route.data.roles && !this.authService.checkPermission(route.data.roles)) {
        this.router.navigate(["/sign-in"], { queryParams: { returnUrl: url } });
        return false;
      }
      return true;
    }

    this.router.navigate(["/sign-in"], { queryParams: { returnUrl: url } });
    return false;
  }
}
