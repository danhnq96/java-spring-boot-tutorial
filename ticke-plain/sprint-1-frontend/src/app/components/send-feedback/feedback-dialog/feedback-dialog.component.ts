import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {SendFeedbackComponent} from "../send-feedback.component";

@Component({
  selector: 'app-feedback-dialog',
  templateUrl: './feedback-dialog.component.html',
  styleUrls: ['./feedback-dialog.component.css']
})
export class FeedbackDialogComponent implements OnInit {


  constructor(public dialogRef: MatDialogRef<SendFeedbackComponent>,
              @Inject(MAT_DIALOG_DATA) public data: 'Bạn đã gửi phản hồi thành công') { }

  ngOnInit() {
  }

}
