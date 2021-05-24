import {Component, OnInit} from '@angular/core';
import {AdminService} from '../../service/admin/admin.service';
import {MatDialog} from '@angular/material/dialog';
import {ChangePasswordAdminComponent} from '../change-password-admin/change-password-admin.component';
import {LoginService} from '../../service/login.service';

@Component({
  selector: 'app-information-admin',
  templateUrl: './information-admin.component.html',
  styleUrls: ['./information-admin.component.css']
})
export class InformationAdminComponent implements OnInit {
  public email;
  public admin;

  constructor(
    public loginService: LoginService,
    public adminService: AdminService,
    public dialog: MatDialog
  ) {
  }

  ngOnInit() {
    // get username account
    this.email = this.loginService.currentUserValue.username;
    // show inf admin
    this.adminService.getAdminByEmailService(this.email).subscribe(data => {
      this.admin = data;
    });
  }

  openDialog(admin): void {
    const dialogRef = this.dialog.open(ChangePasswordAdminComponent, {
      width: '800px',
      data: {dataAdmin: admin},
      disableClose: true
    });
  }
}
