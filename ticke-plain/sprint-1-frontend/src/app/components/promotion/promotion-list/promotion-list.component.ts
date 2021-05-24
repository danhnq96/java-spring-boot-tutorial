import { Component, OnInit } from '@angular/core';
import {PromotionService} from '../promotion.service';
import {MatDialog} from '@angular/material/dialog';
import {DeleteComponent} from './delete/delete.component';

@Component({
  selector: 'app-promotion-list',
  templateUrl: './promotion-list.component.html',
  styleUrls: ['./promotion-list.component.css']
})
export class PromotionListComponent implements OnInit {
  public listPromotion;
  term: any;
  p: any;
  constructor(
    public promotionService: PromotionService,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.promotionService.getAll().subscribe(data => {
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
        width: '450px',
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
