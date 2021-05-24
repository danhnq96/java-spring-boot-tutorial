import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from '../../../auth/token-storage.service';
import { RegisterDTO } from '../../../dto/Employee/RegisterEmployeeDTO';
import { environment } from '../../../../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  httpOptions: any;
  registerURL = `${environment.base_api_url}:${environment.port}/register`;
  constructor(private _httpClient: HttpClient, private tokenStorage: TokenStorageService) {
    this.httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + this.tokenStorage.getToken() })
      , 'Access-Control-Allow-Origin': 'http://localhost:4201', 'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
    };
  };
  /**
   * Resolver
   *
   * @param {ActivatedRouteSnapshot} route
   * @param {RouterStateSnapshot} state
   * @returns {Observable<any> | Promise<any> | any}
   */
  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<any> | Promise<any> | any {

    return new Promise((resolve, reject) => {
      Promise.all([
      ]).then(
        () => {
          resolve();
        },
        reject
      );
    });
  }

  // API Register Employee
  execute_register(employee: RegisterDTO): Promise<RegisterDTO> {
      return new Promise((resolve, reject) => {
        this._httpClient.post<RegisterDTO>(this.registerURL, employee, this.httpOptions)
          .subscribe(() => {
            resolve();
          }, reject);
      });
  }
}
