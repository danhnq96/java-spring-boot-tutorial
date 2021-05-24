import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CheckinOnlineService} from '../../service/checkin_online/checkin-online.service';
import {Router} from '@angular/router';
import {Checkin} from '../../service/ticket/checkin.class';

@Component({
  selector: 'app-checkin-online',
  templateUrl: './checkin-online.component.html',
  styleUrls: ['./checkin-online.component.css']
})
export class CheckinOnlineComponent implements OnInit {
  public formCheckinOnline: FormGroup;
  public checkinResult: Checkin[];
  public message: any;
  public idCheckin = '';
  public messageSuccess = '';
  public selectedItemsList = [];

  constructor(
    public formBuilder: FormBuilder,
    public checkinOnlineService: CheckinOnlineService,
    public router: Router
  ) {
  }

  ngOnInit() {
    this.messageSuccess = '';
    this.formCheckinOnline = this.formBuilder.group({
      bookingCode: ['', [Validators.required]],
      fullName: ['', [Validators.required]]
    });
  }

  searchFlight() {
    // @ts-ignore
    if (this.formCheckinOnline.valid) {
      this.checkinOnlineService.checkinOnline(this.formCheckinOnline.value.fullName,
        this.formCheckinOnline.value.bookingCode).subscribe(data => {
          this.checkinResult = data;
          console.log(this.checkinResult);
          if (this.checkinResult === null ) {
            this.messageSuccess = 'Không tìm thấy thông tin của bạn.';
            setTimeout(() => {
              this.messageSuccess = '';
            }, 5000);
          }
          // this.router.navigateByUrl('checkin-online');
        }
      );
    }
  }

  changeSelection() {
    this.selectedItemsList = this.checkinResult.filter((value, index) => {
      return value.isChecked;
    });
    console.log('this.selectedItemsList');
    console.log(this.selectedItemsList);
  }

  checkInSubmit() {
    // tslint:disable-next-line:prefer-for-of
    for (let i = 0; i < this.selectedItemsList.length; i++) {
      this.idCheckin += (this.selectedItemsList[i].id) + '-#-';
    }
    this.checkinOnlineService.booking(this.idCheckin).subscribe(data => {
      this.messageSuccess = 'checkin online thành công.';
      setTimeout(() => {
        this.messageSuccess = '';
      }, 5000);
    });
    this.checkinResult = undefined;
    this.router.navigateByUrl('checkin-online');
  }

  returnPage() {
    this.checkinResult = undefined;
    this.router.navigateByUrl('checkin-online');
  }
}
