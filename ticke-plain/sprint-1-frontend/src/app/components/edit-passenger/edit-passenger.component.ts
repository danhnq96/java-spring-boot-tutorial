import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PassengerService} from '../../service/passenger/passenger.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-edit-passenger',
  templateUrl: './edit-passenger.component.html',
  styleUrls: ['./edit-passenger.component.css']
})
export class EditPassengerComponent implements OnInit {
  // tslint:disable-next-line:variable-name
  name_msg = 'Họ tên không hợp lệ';
  // tslint:disable-next-line:variable-name
  birthday_msg = 'Ngày tháng năm sinh không hợp lệ';
  // tslint:disable-next-line:variable-name
  idCard_msg = 'Chứng minh nhân dân không hợp lệ';
  // tslint:disable-next-line:variable-name
  phone_msg = 'Số điện thoại không hợp lệ';
  // tslint:disable-next-line:variable-name
  email_msg = 'Email không hợp lệ';
  // tslint:disable-next-line:variable-name
  address_msg = 'Địa chỉ không được để trống';
  public maxDate = new Date();
  public minDate = new Date(1990, 0, 1);
  formEdit: FormGroup;
  luggageList: any;
  private eleId: number;
  private err: any;

  constructor(private formBuilder: FormBuilder,
              private toat: ToastrService,
              private passengerService: PassengerService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    // @ts-ignore
    this.formEdit = this.formBuilder.group({
      fullName: ['', Validators.required],
      birthday: ['', Validators.required],
      address: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern('^((090|091|([\(][\+]84[\)])90|([\(][\+]84[\)])91)[0-9]{7})$')]],
      identityNumber: ['', [Validators.required, Validators.pattern('^[0-9]{9}$')]],
      luggageId: ['', Validators.required],
    });
    // only for Edit
    this.route.params.subscribe(data => {
        this.eleId = data.id;
        this.passengerService.getById(this.eleId).subscribe(dataFromServer => {
          this.formEdit.patchValue(dataFromServer);
        });
      }
    );
    this.passengerService.getLuggage()
      .subscribe(data => this.luggageList = data, error => this.luggageList = []);
  }
  callAPi() {
    console.log(this.formEdit.value);
    this.passengerService.update(this.eleId, this.formEdit.value).subscribe(data => {
      if (data === 1) {
        this.router.navigate(['/passenger/list-Passenger'], {
          queryParams: {
            create_msg: 'update successfully!',
            si: true
          }
        });
        this.toat.success('Thao Tác Thành Công' , 'Thông Báo');
      } else {
        this.toat.error('Thao Tác Thất Bại', 'Thông Báo');
      }
    });
  }
  cancel() {
    this.router.navigate(['/passenger/list-Passenger']);
  }
}
