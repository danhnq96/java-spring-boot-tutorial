import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import {PromotionService} from '../../promotion.service';

// @ts-ignore
@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {
  // tslint:disable-next-line:variable-name
  public flight_number;
  public promotionId;

  constructor(
    public dialogRef: MatDialogRef<DeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    public promotionService: PromotionService
  ) { }

  ngOnInit() {
    this.flight_number = this.data.data1.flightNumber;
    this.promotionId = this.data.data1.id;
  }
  deletePromo() {
    this.promotionService.deletePromo(this.promotionId).subscribe(data => {
        this.dialogRef.close();
      });
  }
}
