import { Component, OnInit } from '@angular/core';
import {TicketService} from '../../../../service/ticket/ticket.service';
import {LoginService} from '../../../../service/login.service';

@Component({
  selector: 'app-payment-history',
  templateUrl: './payment-history.component.html',
  styleUrls: ['./payment-history.component.css']
})
export class PaymentHistoryComponent implements OnInit {
  listBooking;
  constructor(
    private ticketService: TicketService,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    this.ticketService.getAllTicketByAppAccount(this.loginService.currentUserValue.id , 0).subscribe(data => {
      this.listBooking = data;
      console.log(data);
    });
  }
  createFor(lenght): Array<number> {
    const array = [];
    for (let i = 0; i < lenght; i++) {
      array.push(i);
    }
    // @ts-ignore
    return array;
  }

  listHistory(param) {
    this.ticketService.getAllTicketByAppAccount(this.loginService.currentUserValue.id , param).subscribe(data => {
      this.listBooking = data;
      console.log(data);
    });
  }
}
