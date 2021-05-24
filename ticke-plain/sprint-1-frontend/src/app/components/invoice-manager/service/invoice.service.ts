import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Invoice} from '../model/invoice.class';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  public API = 'http://localhost:8080/invoice';

  constructor(
    public http: HttpClient
  ) {
  }
  getAllInvoice(): Observable<Invoice[]> {
    return this.http.get<Invoice[]>(this.API + '/list');
  }

  getListInvoiceById(value): Observable<any[]> {
    return this.http.get<any[]>(this.API + '/listInvoiceById' + value);
  }
  getInvoiceByInvoiceCode(key, capcha): Observable<any> {
    return this.http.get(this.API + '/find/' + key + '&' + capcha);
  }
  getCapcha(): Observable<number> {
    return this.http.get<number>(this.API + '/getCapcha');
  }
}
