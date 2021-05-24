import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {TicketService} from '../../service/ticket/ticket.service';

@Component({
  selector: 'app-print-ticket-two-way',
  templateUrl: './print-ticket-two-way.component.html',
  styleUrls: ['./print-ticket-two-way.component.css']
})
export class PrintTicketTwoWayComponent implements OnInit {
  protected passengerArray = [];
  protected passengerList: string;
  protected bookingCode;
  protected flightDeparture = 'Nothing';
  protected flightArrival = 'Nothing';
  protected flightInformationDeparture = [];
  protected flightInformationArrival = [];
  protected check = 'true';

  constructor(
    private activedRouter: ActivatedRoute,
    protected ticketService: TicketService,
    protected router: Router
  ) {
  }

  ngOnInit() {
    this.activedRouter.params.subscribe(data => {
      this.bookingCode = data.bookingCode;
      this.passengerList = data.passengerList;
      this.flightDeparture = data.flightDeparture;
      if (data.flightArrival !== '0') {
        this.flightArrival = data.flightArrival;
      } else {
        this.check = 'false';
        this.flightArrival = this.flightDeparture;
      }
    });
    this.passengerArray = this.passengerList.split(',', this.passengerList.length);
    this.ticketService.findFlightInformationByIDService(this.flightDeparture).subscribe(
      data => {
        if (data != null) {
          this.flightInformationDeparture = data;
        } else {
          this.error();
        }
      },
      () => {
        this.error();
      },
      () => {
        this.ticketService.findFlightInformationByIDService(this.flightArrival).subscribe(
          data => {
            if (data != null) {
              this.flightInformationArrival = data;
            } else {
              this.error();
            }
          },
          () => {
            this.error();
          }
        );
      }
    );
  }

  private error() {
    const NOTICE = 'Lỗi hệ thống.';
    const URL = 'http://localhost:4200/list-ticket';
    this.router.navigate(['notice-page', {message: NOTICE, path: URL}]).then(r => {
    });
  }
}
