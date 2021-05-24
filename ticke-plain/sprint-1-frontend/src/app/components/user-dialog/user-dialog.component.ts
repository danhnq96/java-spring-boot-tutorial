// @ts-ignore
import { Component, OnInit } from '@angular/core';
// @ts-ignore
import {MatDialogRef} from '@angular/material';
// @ts-ignore
import {Router} from '@angular/router';

import {LoginService} from '../../service/login.service';

// @ts-ignore
@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.css']
})
export class UserDialogComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<UserDialogComponent>,
              private loginService: LoginService,
              private route: Router) { }

  ngOnInit() {
    this.loginService.logout();
    this.route.navigateByUrl('/login');
  }

}
