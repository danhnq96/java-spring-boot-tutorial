import { Component, OnInit } from '@angular/core';
import {PassengerService} from '../../service/passenger/passenger.service';
import {MatDialog} from '@angular/material/dialog';
import {ActivatedRoute} from '@angular/router';
import {DeletePassengerComponent} from '../delete-passenger/delete-passenger.component';

@Component({
  selector: 'app-list-passenger',
  templateUrl: './list-passenger.component.html',
  styleUrls: ['./list-passenger.component.css']
})
export class ListPassengerComponent implements OnInit {
  private currentPage = 0;
  private passengers: Array<any>;
  private pages: Array<number>;
  private by = 'all';
  private search = '';
  public hide = false;

  constructor(private passengerService: PassengerService ,
              public dialog: MatDialog,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.getPassenger();
  }
  getPassenger() {
    this.passengerService.search(this.currentPage, this.by, this.search).subscribe(data => {
      this.passengers = data.content;
      console.log(data);
      console.log(this.passengers);
      this.pages = new Array(data.totalPages);
    });
  }

  previousClick() {
    if (this.currentPage > 0) {
      this.currentPage--;
      this.ngOnInit();
    }
  }

  nextClick() {
    if (this.currentPage < this.pages.length - 1) {
      this.currentPage++;
      this.ngOnInit();
    }
  }

  getPaginationWithIndex(i: any) {
    this.currentPage = i;
    this.ngOnInit();
  }

  findPassenger() {
    this.ngOnInit();
  }


  openDeleteDialog(id: number) {
    this.passengerService.getById(id).subscribe(dataFromServer => {
      const dialogRef = this.dialog.open(DeletePassengerComponent, {
        width: '500px',
        disableClose: true,
        data: {data1: dataFromServer}
      });

      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.ngOnInit();
      });
    });

  }
}
