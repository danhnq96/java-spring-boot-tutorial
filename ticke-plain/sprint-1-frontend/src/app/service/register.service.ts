import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  readonly API = 'http://localhost:8080/register';

  constructor(public http: HttpClient) {
  }

  register(user): Observable<any> {
    return this.http.post(this.API, user, {headers: {skip: 'true'}});
  }
}
