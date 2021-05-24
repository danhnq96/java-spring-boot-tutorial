import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  public API_GET_INFO_ADMIN = 'http://localhost:8080/admin/findByEmail/';

  public API_GET_APP_ACCOUNT = 'http://localhost:8080/changePassword/findAppAccountByUsername/';

  public API_SEND_EMAIL = 'http://localhost:8080/changePassword/confirmEmail/';

  public API_VERIFY_EMAIL = 'http://localhost:8080/changePassword/verify/';

  public API_SAVE_PASSWORD_APP_ACCOUNT = 'http://localhost:8080/changePassword/saveAppAccount/';

  constructor(public http: HttpClient) {
  }

  // get information admin
  getAdminByEmailService(email: any): Observable<any> {
    return this.http.get(this.API_GET_INFO_ADMIN + email);
  }

  // get object AppAccount
  findAppAccountService(username): Observable<any> {
    return this.http.get(this.API_GET_APP_ACCOUNT + username);
  }

  // send Email
  sendConfirmEmailService(username): Observable<any> {
    // @ts-ignore
    return this.http.post(this.API_SEND_EMAIL + username);
  }

  // verify email to confirm change password
  confirmEmail(username, account): Observable<any> {
    return this.http.put(this.API_VERIFY_EMAIL + username, account);
  }


  // save object AppAccount && password
  savePasswordAdminService(username, account): Observable<any> {
    return this.http.put(this.API_SAVE_PASSWORD_APP_ACCOUNT + username, account);
  }
}

