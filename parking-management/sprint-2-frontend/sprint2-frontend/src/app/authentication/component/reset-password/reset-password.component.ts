import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../service/auth/authentication.service';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {ToastrService} from 'ngx-toastr';

function comparePassword(c: AbstractControl): any {
  const v = c.value;
  const isNotEmpty = v.confirmPassword !== '';
  if (isNotEmpty) {
    return (v.passwordNew === v.confirmPassword) ? null : {
      passwordNotMatch: true
    };
  }
}

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})

export class ResetPasswordComponent implements OnInit {
  message = '';
  checkEmail: boolean;
  codeBackend: string;
  checkResultEmail: boolean;
  checkCode: boolean;
  checkReset: boolean;
  sendEmail: FormGroup;
  reset: FormGroup;
  constructor(private authenticationService:
                AuthenticationService,
              private toastrService: ToastrService,
              private fb: FormBuilder,
              private router: Router,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.checkEmail = false;
    this.checkCode = false;
    this.checkReset = false;
    this.checkResultEmail = true;
    this.sendEmail = this.fb.group({
      email: ['', [Validators.required, Validators.pattern(/^[a-z][a-z0-9_\.]{3,32}@[a-z0-9]{2,}(\.[a-z0-9]{2,4}){1,2}$/)]],
    });
    this.reset = this.fb.group({
      passwordNew: ['', [Validators.required, Validators.pattern(/^[a-z0-9A-Z]{6,30}$/)]],
      confirmPassword: ['', [Validators.required]],
    }, {validator: comparePassword});
  }

  sendEmailReset(): void {
    this.authenticationService.send(this.sendEmail.value.email).subscribe(next => {
      console.log(next);
      if (next == null){
        this.checkEmail = false;
        this.checkResultEmail = false;
      }else {
        this.codeBackend = next;
        setTimeout (() => {
          this.codeBackend = null;
        }, 120000);
        this.checkEmail = true;
        this.checkResultEmail = true;
        this.toastrService.success('G????i mail tha??nh c??ng, ba??n co?? 2 phu??t ?????? nh????p ma?? xa??c nh????n!', 'Th??ng ba??o!');
      }
    });
  }

  confirmPassword(value: string): void {
    if (this.codeBackend == null){
      this.message = 'M?? c???a b???n ???? h???t th???i h???n s??? d???ng.';
    }else {
      if (this.codeBackend == value){
        this.checkCode = true;
      }else {
        this.message = 'M?? n??y kh??ng h???p l???, vui l??ng ki???m tra l???i mail c???a b???n.';
      }
    }
  }

  resetPassword(): void {
    this.authenticationService.resetPassWord(this.reset.value.passwordNew).subscribe(data => {
      this.checkReset = data;
      this.toastrService.success('??????i m????t kh????u tha??nh c??ng!', 'Th??ng ba??o!');
      if (this.checkReset === true) {
        this.dialog.closeAll();
      //  thoong baos
      }
    });
  }
}

