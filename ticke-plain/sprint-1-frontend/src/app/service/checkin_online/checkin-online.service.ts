import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckinOnlineService {
  public readonly API: string = 'http://localhost:8080/checkinOnline';
  constructor(
    public http: HttpClient
  ) {}
  checkinOnline(fullName, bookingCode): Observable<any> {
    return this.http.get(this.API + '/findInfoFlight/' + fullName + '&' + bookingCode);
  }
  booking(value): Observable<any> {
    let params = new HttpParams();
    params = params.append('idCheckin', value);
    return this.http.get(this.API + '/checkStatusCheckin', {params});
  }

}
