import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-flight-detail',
  templateUrl: './flight-detail.component.html',
  styleUrls: ['./flight-detail.component.css']
})
export class FlightDetailComponent implements OnInit {
  flight;

  constructor(
    private dialogRef: MatDialogRef<FlightDetailComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any
  ) {
  }

  ngOnInit() {
    this.flight = this.data.flightDetails;
  }

  cancel() {
    this.dialogRef.close();
  }
}
