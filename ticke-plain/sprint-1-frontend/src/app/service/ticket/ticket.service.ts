import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, timer} from 'rxjs';
import {AbstractControl, AsyncValidatorFn} from '@angular/forms';
import {map, switchMap} from 'rxjs/operators';
import {TicketStatusPaymentDTO} from '../../model/TicketStatusPaymentDTO';

const API = 'http://localhost:8080/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketService {
  protected readonly API: string = 'http://localhost:8080/ticket';
  public apiAdd = 'http://localhost:8080/ticket/add';
  public apiTicket = 'http://localhost:8080/ticket/find-ticket-app-user';
  private apiSentMail ='http://localhost:8080/ticket/sent-mail?idAccount=';

  constructor(
    protected http: HttpClient
  ) {
  }

  getAllTicketService(): Observable<any> {
    return this.http.get(this.API + '/list');
  }

  /*
  * Đăng start
  * */
  findAllPendingTicket(): Observable<any> {
    return this.http.get(this.API + '/list/Pending');
  }

  setTicketStatusPayment(id: number, ticketStatusPaymentDTO: TicketStatusPaymentDTO): Observable<any> {
    // console.log(this.API + '/set-status-payment/' + id, ticketStatusPaymentDTO);
    return this.http.put(this.API + '/set-status-payment/' + id, ticketStatusPaymentDTO);
  }

  searchTicket(ticketSearchDTO: any): Observable<any> {
    // console.log('ticketSearchDTO');
    // console.log(ticketSearchDTO);
    // console.log('URL');
    // console.log(this.API + '/search?' + 'statusPaymentName=' + ticketSearchDTO.statusPaymentName +
    // 	'&searchBy=' + ticketSearchDTO.searchBy + '&searchValue=' + ticketSearchDTO.searchValue);
    // return this.http.get(this.API + '/list/Pending');
    return this.http.get(this.API + '/search?' +
      'statusPaymentName=' + ticketSearchDTO.statusPaymentName +
      '&searchBy=' + ticketSearchDTO.searchBy +
      '&searchValue=' + ticketSearchDTO.searchValue);
  }

  /*
  * Đăng end
  * */



  /**
   * Chau start
   */
  findTicketByIDService(idFind): Observable<any> {
    return this.http.get(this.API + '/find-by-id/' + idFind);
  }

  findFlightInformationByIDService(idFind): Observable<any> {
    return this.http.get(this.API + '/find-flight-information-by-id/' + idFind);
  }

  deleteTicketService(idDelete): Observable<any> {
    return this.http.delete(this.API + '/delete/' + idDelete);
  }

  editTicketService(passengerEdit, appUserEdit, ticketEdit): Observable<any> {
    return this.http.put(this.API + '/edit/' + passengerEdit + '/' + appUserEdit, ticketEdit);
  }

  saveTicketService(idFlightInformationDeparture, idFlightInformationArrival, ticket): Observable<any> {
    return this.http.post(this.API + '/save/' + idFlightInformationDeparture + '/' +
      idFlightInformationArrival, ticket);
  }

  validateEmailUser(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<{ [key: string]: any } | null> => {
      return this.searchEmailUser(control.value)
        .pipe(
          map(res => {
            if (!res.length) {
              return {emailUserExists: true};
            }
          })
        );
    };
  }

  searchEmailUser(emailUser) {
    return timer(50)
      .pipe(
        switchMap(() => {
          return this.http.get<any>(API + '/app-user/' + emailUser);
        })
      );
  }

  openNewWindow() {
    return window;
  }
  /**
   * Chau end
   */

  // khanh
  findAllTicketByInvoiceId(invoiceId): Observable<any> {
    return this.http.get(this.API + '/findAllTicketByInvoiceId/' + invoiceId);
  }

  // Din
  getAllTicketByAppAccount(idAccount, page): Observable<any> {
    return this.http.get(this.apiTicket + '/' + idAccount + '/' + page);
  }

  addTicketAndBooking(daoTicket): Observable<any> {
    return this.http.post(this.apiAdd, daoTicket);
  }
  // Đăng
  sentEmail(id: number): Observable<any>  {
    return this.http.get(this.apiSentMail + id);
  }
}
