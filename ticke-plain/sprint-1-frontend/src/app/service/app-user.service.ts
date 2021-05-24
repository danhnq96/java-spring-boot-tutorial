import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AppUserService {
  public API_USER = 'http://localhost:8080/invoice';

  constructor(private http: HttpClient) {
  }

  findAppUserById(idUser): Observable<any> {
    return this.http.get(this.API_USER + '/findAppUserById/' + idUser);
  }
}
