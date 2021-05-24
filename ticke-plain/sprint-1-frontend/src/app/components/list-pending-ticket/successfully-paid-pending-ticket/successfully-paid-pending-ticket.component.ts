import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {TicketService} from "../../../service/ticket/ticket.service";

@Component({
  selector: 'app-successfully-paid-pending-ticket',
  templateUrl: './successfully-paid-pending-ticket.component.html',
  styleUrls: ['./successfully-paid-pending-ticket.component.css']
})
export class SuccessfullyPaidPendingTicketComponent implements OnInit {
  public payTicketList;

  constructor(
      public dialogRef: MatDialogRef<SuccessfullyPaidPendingTicketComponent>,
      @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit() {
    this.payTicketList = this.data.dataSuccessfullyPaid;
    console.log(this.payTicketList);
  }
  
  closeSuccessfullyPaidPendingTicketDialog() {
    this.dialogRef.close();
  }
}
