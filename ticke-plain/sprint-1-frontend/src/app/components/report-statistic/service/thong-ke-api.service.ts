import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ThongKeApiService {
  public API = 'http://localhost:8080/statistic';
  constructor(
    public http: HttpClient
  ) { }
  // @ts-ignore
  getAllStatistic(): Observable<any>{
    return this.http.get(this.API);
  }
  // @ts-ignore
  findStatisticByID(idStatistic): Observable<any>{
    return this.http.get(this.API + '/' + idStatistic);
  }
}
