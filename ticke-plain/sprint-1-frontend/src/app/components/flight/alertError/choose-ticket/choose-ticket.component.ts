import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-choose-ticket',
  templateUrl: './choose-ticket.component.html',
  styleUrls: ['./choose-ticket.component.css']
})
export class ChooseTicketComponent implements OnInit {
  message;
  constructor(
    private dialogRef: MatDialogRef<ChooseTicketComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {
  }

  ngOnInit() {
    this.message = this.data.message;
  }
  close() {
    this.dialogRef.close();
  }

}
