import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {InfoPassengerBookingTicketComponent} from "../info-passenger-booking-ticket/info-passenger-booking-ticket.component";

@Component({
  selector: 'app-flight-ticket',
  templateUrl: './flight-ticket.component.html',
  styleUrls: ['./flight-ticket.component.css']
})
export class FlightTicketComponent implements OnInit {
  flightOne;
  flightTwo;
  quantityTicketOne;
  quantityTicketTwo;

  constructor(
    private dialogRef: MatDialogRef<FlightTicketComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any,
    private dialog: MatDialog
  ) {
  }

  ngOnInit() {
    this.flightOne = this.data.flightDetailsOne;
    this.flightTwo = this.data.flightDetailsTwo;
    if (this.data.quantity !== '') {
      this.quantityTicketOne = this.data.quantity;
      this.quantityTicketTwo = this.data.quantity;
    } else {
      this.quantityTicketOne = '1';
      this.quantityTicketTwo = '1';
    }
  }

  cancel() {
    this.dialogRef.close();
  }

  dialogPassenger() {
    this.dialogRef.close();
    const dialogRef = this.dialog.open(InfoPassengerBookingTicketComponent, {
      width: '80%',
      height: '100%',
      data: {
        flightDetailOne: this.flightOne,
        flightDetailTwo: this.flightTwo,
        quantityOne: this.quantityTicketOne,
        quantityTwo: this.quantityTicketTwo
      },
      disableClose: true,
      hasBackdrop: true
    });
    dialogRef.afterClosed().subscribe(data => {
      this.ngOnInit();
    });
  }
}
