import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BaoCaoService {
  public API = 'http://localhost:8080/report';
  constructor(
    public http: HttpClient
  ) { }
  // @ts-ignore
  addNewReport(report): Observable<any>{
    return this.http.post(this.API, report);
  }
  getAllReport(): Observable<any>{
    return this.http.get(this.API);
  }
}
