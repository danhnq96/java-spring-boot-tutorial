import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {TicketService} from '../../../service/ticket/ticket.service';
import {TicketStatusPaymentDTO} from '../../../model/TicketStatusPaymentDTO';

@Component({
  selector: 'app-cancel-pending-ticket',
  templateUrl: './cancel-pending-ticket.component.html',
  styleUrls: ['./cancel-pending-ticket.component.css']
})
export class CancelPendingTicketComponent implements OnInit {
  public ticketCancel;
  public ticketStatusPaymentDTO = {id: 1, statusPaymentName: ''};

  constructor(
      public dialogRef: MatDialogRef<CancelPendingTicketComponent>,
      public ticketService: TicketService,
      @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    this.ticketCancel = this.data.dataCancel;
    console.log('this.ticketCancel');
    console.log(this.ticketCancel);
  }

  cancelTicket() {
    this.ticketStatusPaymentDTO.id = this.ticketCancel.id;
    this.ticketStatusPaymentDTO.statusPaymentName = 'Cancel';
    console.log('this.ticketStatusPaymentDTO');
    console.log(this.ticketStatusPaymentDTO);
    this.ticketService.setTicketStatusPayment(this.ticketStatusPaymentDTO.id, this.ticketStatusPaymentDTO).subscribe(value => {
      this.dialogRef.close();
    });
  //  csdvsdvsdv
  //
  }
}
