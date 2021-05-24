import { Component, OnInit } from '@angular/core';
import {PromotionService} from '../../promotion.service';
import {MatDialog} from '@angular/material/dialog';
import {DeleteComponent} from '../../promotion-list/delete/delete.component';

@Component({
  selector: 'app-sat',
  templateUrl: './sat.component.html',
  styleUrls: ['./sat.component.css']
})
export class SatComponent implements OnInit {
  listPromotion: any;
  term: string;

  constructor(
    public promotionService: PromotionService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.promotionService.getPromoInSat().subscribe(data => {
      this.listPromotion = data;
      for (const cus of this.listPromotion) {
        cus.arrivalTime = cus.departureDate + ' ' + cus.arrivalTime;
        cus.departureTime = cus.departureDate + ' ' + cus.departureTime;
      }
    });
  }

  confirmDelete(promotionid): void {
    console.log(promotionid);
    this.promotionService.getPromoById(promotionid).subscribe(dataOfPromotion => {
      const dialogRef = this.dialog.open(DeleteComponent, {
        width: '250px',
        data: {data1: dataOfPromotion},
        disableClose: true
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.ngOnInit();
      });
    });
  }
}
