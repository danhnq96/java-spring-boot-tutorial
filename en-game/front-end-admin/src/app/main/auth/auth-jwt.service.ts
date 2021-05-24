import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { JwtResponse } from './jwt-response';
import { AuthLoginInfo } from './login-info';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  // ,'Access-Control-Allow-Origin': 'http://localhost:4201'
};
@Injectable({
  providedIn: 'root'
})

export class AuthJwtService {
  loginUrl = "http://localhost:8762/api/admin/login/"
  constructor(private httpClient: HttpClient) { }

  /**
   * Resolver
   *
   * @param {ActivatedRouteSnapshot} route
   * @param {RouterStateSnapshot} state
   * @returns {Observable<any> | Promise<any> | any}
   */

  public attemptAuth(userinfo: AuthLoginInfo): Promise<JwtResponse> {
    return new Promise((resolve, reject) => {
      this.httpClient.post<JwtResponse>(this.loginUrl, userinfo, httpOptions)
        .subscribe((response: JwtResponse) => {
          resolve(response);
        }, reject);
    });
  }
}
