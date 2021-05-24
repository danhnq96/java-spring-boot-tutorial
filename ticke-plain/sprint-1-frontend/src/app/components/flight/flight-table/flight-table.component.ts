import {Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {FlightService} from '../../../service/flight-information/flight.service';
import {MatDialog, MatDialogRef} from '@angular/material/dialog';
import {FlightDetailComponent} from '../flight-detail/flight-detail.component';
import {FlightTicketComponent} from '../flight-ticket/flight-ticket.component';
import {ChooseTicketComponent} from '../alertError/choose-ticket/choose-ticket.component';

@Component({
  selector: 'app-flight-table',
  templateUrl: './flight-table.component.html',
  styleUrls: ['./flight-table.component.css']
})
export class FlightTableComponent implements OnInit, OnChanges {
  @Input()
  listOneWayTable = null;
  @Input()
  listReturnTable = null;
  @Input()
  dateDeparture;
  @Input()
  dateArrival;
  @Input()
  departure;
  @Input()
  arrival;
  @Input()
  sortChildren;
  @Input()
  quantityInChildrenCpn;
  @Input()
  wayChildren;
  dialogRef;
  listIdFlight = {
    flightIdOne: '',
    flightIdTwo: ''
  };
  arraysOneWay;
  arraysReturn;

  constructor(private flightService: FlightService,
              private dialog: MatDialog) {
  }

  ngOnInit() {
  }

  ngOnChanges(): void {
    this.listIdFlight.flightIdTwo = '';
    this.listIdFlight.flightIdOne = '';
    const dateDepartureTempt = Date.parse(this.dateDeparture);
    this.arraysOneWay = [new Date(dateDepartureTempt).toString(), new Date(dateDepartureTempt + 3600 * 24 * 1000).toString(),
      new Date(dateDepartureTempt + 3600 * 24 * 2 * 1000).toString(),
      new Date(dateDepartureTempt + 3600 * 24 * 3 * 1000).toString(),
      new Date(dateDepartureTempt + 3600 * 24 * 4 * 1000).toString()];
    if (this.listReturnTable !== undefined) {
      const dataArrivalTempt = Date.parse(this.dateArrival);
      this.arraysReturn = [new Date(dataArrivalTempt).toString(), new Date(dataArrivalTempt + 3600 * 24 * 1000).toString(),
        new Date(dataArrivalTempt + 3600 * 24 * 2 * 1000).toString(),
        new Date(dataArrivalTempt + 3600 * 24 * 3 * 1000).toString(),
        new Date(dataArrivalTempt + 3600 * 24 * 4 * 1000).toString()];
    }
  }

  searchOneWayPage(departureDate?, page?) {
    if (page === undefined) {
      page = 0;
    }
    // Để lấy ngày tháng năm đi theo dạng dd/mm/yyyy
    if (departureDate !== undefined) {
      this.dateDeparture = departureDate;
    }
    const dateDepartureTempt = new Date(this.dateDeparture).toLocaleDateString();
    // Lấy list Flight đi
    this.flightService.search(page, this.departure, this.arrival,
      dateDepartureTempt, this.sortChildren).subscribe(data => {
      this.listOneWayTable = data;
    });
  }

  searchReturnPage(arrivalDate?, page?) {
    if (page === undefined) {
      page = 0;
    }
    if (arrivalDate !== undefined) {
      this.dateArrival = arrivalDate;
    }
    const dataArrivalTempt = new Date(this.dateArrival).toLocaleDateString();
    // Lấy list Flight đi
    this.flightService.search(page, this.arrival, this.departure,
      dataArrivalTempt, this.sortChildren).subscribe(data => {
      this.listReturnTable = data;
    });
  }

  dialogFlightInformation(id): void {
    this.flightService.getById(id).subscribe(flight => {
        const dialogRef = this.dialog.open(FlightDetailComponent, {
          width: '80%',
          data: {flightDetails: flight},
          disableClose: true,
          hasBackdrop: true
        });
        dialogRef.afterClosed().subscribe(data => {
          this.ngOnInit();
        });
      }
    );
  }

  flightTwo(id) {
    this.listIdFlight.flightIdTwo = id;
  }

  flightOne(id) {
    this.listIdFlight.flightIdOne = id;
  }

  dialogTicketChoose() {
    this.flightService.getById(this.listIdFlight.flightIdOne).subscribe(flightOneData => {
      const quantityTemp = this.quantityInChildrenCpn;
      if (this.wayChildren === '2') {
        this.flightService.getById(this.listIdFlight.flightIdTwo).subscribe(flightTwoData => {
          this.dialogRef = this.dialog.open(FlightTicketComponent, {
            width: '80%',
            data: {flightDetailsOne: flightOneData, flightDetailsTwo: flightTwoData, quantity: quantityTemp},
            disableClose: true,
            hasBackdrop: true
          });
        });
      } else {
        this.dialogRef = this.dialog.open(FlightTicketComponent, {
          width: 'auto',
          data: {flightDetailsOne: flightOneData, flightDetailsTwo: '', quantity: quantityTemp},
          disableClose: true,
          hasBackdrop: true
        });
        this.dialogRef.afterClosed().subscribe(data => this.ngOnInit());
      }
    });
  }

  errorChooseTicket(): string {
    /* Trường hợp người dùng chọn một chiều
    *  Chưa chọn vé tàu
    */
    if (this.wayChildren === '1' && this.listIdFlight.flightIdOne === '') {
      return 'Bạn chưa chọn vé tàu';
    }
    /* Trường hợp người dùng chọn khứ hồi
    *  Chưa chọn vé tàu
    */
    if (this.wayChildren === '2' && this.listIdFlight.flightIdOne === '' && this.listIdFlight.flightIdTwo === '') {
      return 'Bạn chưa chọn vé tàu';
    }
    /* Trường hợp người dùng chọn khứ hồi
   *  Chưa chọn vé tàu đi
   */
    if (this.wayChildren === '2' && this.listIdFlight.flightIdOne === '') {
      return 'Bạn chưa chọn vé tàu đi';
    }
    /* Trường hợp người dùng chọn khứ hồi
   *  Chưa chọn vé tàu về
   */
    if (this.wayChildren === '2' && this.listIdFlight.flightIdTwo === '') {
      return 'Bạn chưa chọn vé tàu về';
    } else {
      return 'Ok';
    }
  }

  dialogTicket() {
    const messageError = this.errorChooseTicket();
    if (messageError !== 'Ok') {
      this.dialogRef = this.dialog.open(ChooseTicketComponent, {
        width: '20%',
        disableClose: true,
        hasBackdrop: true,
        data: {message: messageError}
      });
      this.dialogRef.afterClosed().subscribe(data => this.ngOnInit());
      ;
    } else {
      this.dialogTicketChoose();
    }
  }

  createFor(lenght): Array<number> {
    const array = [];
    for (let i = 0; i < lenght; i++) {
      array.push(i);
    }
    // @ts-ignore
    return array;
  }
}
