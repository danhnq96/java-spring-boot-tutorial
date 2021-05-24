import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FeedbackService {

  private feedBackUrl = 'http://localhost:8080/';

  constructor(private http: HttpClient) {
  }

  public sendFeedBack(feedback): Observable<any> {
    return this.http.post(this.feedBackUrl + 'feedback/send-feedback', feedback);
  }
}
