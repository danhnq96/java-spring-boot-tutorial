// @ts-ignore
import { Component, OnInit } from '@angular/core';
import {ChangePasswordUserComponent} from '../change-password-user/change-password-user.component';
// @ts-ignore
import {MatDialog} from '@angular/material';

// @ts-ignore
@Component({
  selector: 'app-information-user',
  templateUrl: './information-user.component.html',
  styleUrls: ['./information-user.component.css']
})
export class InformationUserComponent implements OnInit {

  constructor(
    private dialog: MatDialog
  ) { }

  ngOnInit() {
  }
  // Trần  Đạt - Hiển thị Dialog đổi mật khẩu.
  openDialogChangePassword() {
    const dialogRef = this.dialog.open(ChangePasswordUserComponent, {
      width: '750px',
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.ngOnInit();
    });
  }

}
