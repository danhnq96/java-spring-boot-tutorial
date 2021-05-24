import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookingService {
  private api = 'http://localhost:8080/booking/list';

  constructor(private httpClient: HttpClient) {
  }

  getAllPassenger(page): Observable<any> {
    return this.httpClient.get(this.api + '?page=' + page);
  }
}
