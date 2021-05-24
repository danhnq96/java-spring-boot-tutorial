import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {AdminService} from '../../service/admin/admin.service';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Router} from '@angular/router';

@Component({
  selector: 'app-change-password-admin',
  templateUrl: './change-password-admin.component.html',
  styleUrls: ['./change-password-admin.component.css']
})
export class ChangePasswordAdminComponent implements OnInit {
  public changePasswordForm: FormGroup;
  public username;
  public account;

  matcher = new ErrorStateMatcher();

  constructor(public formBuilder: FormBuilder,
              public adminService: AdminService,
              public route: Router,
              public dialogRef: MatDialogRef<ChangePasswordAdminComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.changePasswordForm = this.formBuilder.group({
      passwordOld: ['',
        [
          Validators.required,
          Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
        ]
      ],
      passwordNew: ['',
        [
          Validators.required,
          Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
        ]
      ],
      confirmPassword: ['',
        [
          Validators.required,
          Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,20}$')
        ]
      ],
      verificationCode: ['', Validators.required],
    }, {validator: this.checkPasswordConfirm});
  }

  checkPasswordConfirm(group: FormGroup) {
    const passwordNew = group.controls.passwordNew.value;
    const confirmPass = group.controls.confirmPassword.value;
    return passwordNew === confirmPass ? null : {notSame: true};
  }


  ngOnInit() {
    this.username = this.data.dataAdmin.email;
    // send email confirm
    this.adminService.sendConfirmEmailService(this.username).subscribe(dataSendEmail => {
    });
  }

  changePassword() {
    this.adminService.findAppAccountService(this.username).subscribe(dataAppAccount => {
      this.account = {
        passwordOld: this.changePasswordForm.controls.passwordOld.value,
        passwordNew: this.changePasswordForm.controls.passwordNew.value,
        verificationCode: this.changePasswordForm.controls.verificationCode.value
      };
      this.adminService.confirmEmail(dataAppAccount.username, this.account).subscribe(dataConfirmEmail => {
        if (dataConfirmEmail) {
          this.adminService.savePasswordAdminService(dataAppAccount.username, this.account).subscribe(dataSavePassword => {
            console.log(dataSavePassword.message);
            if (dataSavePassword.message === 'Password changed') {
              this.dialogRef.close();
              this.route.navigateByUrl('/admin/change-password-successfully');
            } else {
              this.dialogRef.close();
              this.route.navigateByUrl('/admin/get-check-password');
            }
          });
        } else {
          this.dialogRef.close();
          this.route.navigateByUrl('/admin/get-token-email');
        }
      });
    });
  }
}
