import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from '@angular/forms';
import {PassengerService} from '../../service/passenger/passenger.service';
import {Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-create-passenger',
  templateUrl: './create-passenger.component.html',
  styleUrls: ['./create-passenger.component.css']
})
export class CreatePassengerComponent implements OnInit {
  // tslint:disable-next-line:variable-name
  name_msg = 'Họ tên không hợp lệ';
  // tslint:disable-next-line:variable-name
  birthday_msg = 'Ngày tháng năm sinh không hợp lệ';
  // tslint:disable-next-line:variable-name
  idCard_msg = 'Chứng minh nhân dânkhông hợp lệ';
  // tslint:disable-next-line:variable-name
  phone_msg = 'Số điện thoại không hợp lệ';
  // tslint:disable-next-line:variable-name
  email_msg = 'Email không hợp lệ';
  // tslint:disable-next-line:variable-name
  address_msg = 'Địa chỉ không được để trống';
  formCreate: any;
  public maxDate = new Date();
  public minDate = new Date(1990, 0, 1);
  luggageList: any;

  constructor(private formBuilder: FormBuilder,
              private passengerService: PassengerService,
              private router: Router,
              public toast: ToastrService,
              private toat: ToastrService,
              private datePipe: DatePipe) { }

  ngOnInit() {
    this.formCreate = this.formBuilder.group({
      fullName: ['', Validators.required],
      birthday: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern('^((090|091|([\(][\+]84[\)])90|([\(][\+]84[\)])91)[0-9]{7})$')]],
      identityNumber: ['', [Validators.required, Validators.pattern('^[0-9]{9}$')]],
      luggageId: ['', Validators.required],
    });
    // @ts-ignore
    this.passengerService.getLuggage().subscribe(value =>
      this.luggageList = value, error => this.luggageList = [],
    );
  }

  callApi() {
    this.passengerService.create(this.formCreate.value).subscribe(value => {
        if (value === 1) {
          this.router.navigate(['/passenger/list-Passenger'], {queryParams: {create_msg: 'Create successfully!', si: true}});
          this.toast.success('Thao Tác Thành Công', 'Thông Báo');
        } else {
          this.toast.error('Thao Tác Thất Bại', 'Thông Báo');
        }
      }
    );
  }
  cancel() {
    this.router.navigate(['/passenger/list-Passenger']);
  }

}
