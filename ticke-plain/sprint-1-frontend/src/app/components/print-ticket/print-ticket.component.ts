import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TicketService} from '../../service/ticket/ticket.service';

@Component({
  selector: 'app-print-ticket',
  templateUrl: './print-ticket.component.html',
  styleUrls: ['./print-ticket.component.css']
})
export class PrintTicketComponent implements OnInit {
  protected ticket = [];
  protected idNeed;
  protected bookingCode;
  protected flightInformationDisplay = [];

  constructor(
    private activatedRoute: ActivatedRoute,
    protected ticketService: TicketService,
    protected router: Router
  ) {
  }

  ngOnInit() {
    this.activatedRoute.params.subscribe(data => {
      this.idNeed = data.id;
    });
    this.ticketService.findTicketByIDService(this.idNeed).subscribe(
      ticket => {
      if (ticket != null) {
        this.bookingCode = ticket.booking.bookingCode;
        this.flightInformationDisplay = ticket.flightInformation;
        this.ticket = ticket;
      } else {
        const NOTICE = 'Không tìm thấy vé.';
        const URL = 'http://localhost:4200/list-ticket';
        this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
        });
      }
    },
      () => {
        const NOTICE = 'Lỗi hệ thống';
        this.router.navigate(['notice-page', {message: NOTICE}]).then(r => {
        });
      }
    );
  }
}
