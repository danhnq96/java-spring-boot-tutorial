import {Component, OnInit} from '@angular/core';
import {UserService} from '../../service/user/user.service';
import {LoginService} from '../../service/login.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EditUserComponent} from '../edit-user/edit-user.component';
import {MatDialog} from '@angular/material';
import {datepickerAnimation} from 'ngx-bootstrap/datepicker/datepicker-animations';
import {getDate} from 'ngx-bootstrap/chronos/utils/date-getters';
import {createUTCDate} from 'ngx-bootstrap/chronos/create/date-from-array';


@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html',
  styleUrls: ['./detail-user.component.css']
})
export class DetailUserComponent implements OnInit {

  constructor(
    private dialog: MatDialog,
    private userService: UserService,
    private loginService: LoginService ) {
  }

  public users;
  public idUser;
  infoFormUser: FormGroup;
  // Trần  Đạt - Dialog chỉnh sửa thông tin.;

  // Trần  Đạt -  Form hiển thị thông tin khách hàng.
  ngOnInit(): void {
    this.idUser = this.loginService.currentUserValue.id;
    this.userService.getUserById(this.idUser).subscribe(data => {
        console.log(this.idUser);
        console.log(data);
        this.users = data;
        if (this.users.gender === false) {
          this.users.gender = 'Nữ';
        } else {
          this.users.gender = 'Nam';
        }
      }
    );

  }

  openDialogEdit() {
    this.idUser = this.loginService.currentUserValue.id;
    console.log(this.idUser);
    this.userService.getUserById(this.idUser).subscribe(dataEdit => {
      const dialogRef = this.dialog.open(EditUserComponent, {
        width: '900px',
        data: {dataE: dataEdit.id}
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.ngOnInit();
      });
    });
  }
}
