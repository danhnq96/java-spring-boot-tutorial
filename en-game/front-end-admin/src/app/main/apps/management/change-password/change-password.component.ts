import { Component, OnInit } from '@angular/core';
import { ValidatorFn, AbstractControl, ValidationErrors, FormGroup, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenStorageService } from 'app/main/auth/token-storage.service';
import { Notification } from 'app/main/commons/Notification';
import { ManagementChangePasswordService } from './change-password.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ManagementChangePasswordComponent implements OnInit {
  employeeForm: FormGroup;
  constructor(private formBuilder: FormBuilder, private changePasswordService: ManagementChangePasswordService, private tokenService: TokenStorageService,
    private router: Router,) {
    this.employeeForm = this.createEmployeeForm();
  }

  ngOnInit(): void {
    // console.log("x");
  }

  // change password
  public changePassword(): void {
    if (!this.employeeForm.invalid) {
      Notification.showWaiting();
      const pass: string[] = [this.employeeForm.get("password").value, this.employeeForm.get("passwordNew").value];
      this.changePasswordService
        .changePassword(pass)
        .then(() => {
          Notification.showSuccess("Change Your Password Successfully.", "Please login again.", "OK");
          // reset value for input
          this.tokenService.logOut();
          this.router.navigate(["/login"]);
        })
        .catch((error) => {
          if (error.status === 401) {
            Notification.showErrorMessage("Error", "Password Old Is Incorrect");
            this.employeeForm.get("password").setValue("");
          } else {
            Notification.showErrorStatus(error);
          }
        })
    }
  }

  private createEmployeeForm(): FormGroup {
    return this.formBuilder.group({
      password: [""],
      // eslint-disable-next-line no-use-before-define
      passwordNew: ["", [confirmPasswordValidator]],
      // eslint-disable-next-line no-use-before-define
      passwordConfirm: ["", [confirmPasswordValidator]],
    });
  }


}
/**
 * Confirm password validator
 *
 * @param {AbstractControl} control
 * @returns {ValidationErrors | null}
 */
export const confirmPasswordValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  if (!control.parent || !control) {
    return null;
  }

  const passwordNew = control.parent.get("passwordNew");
  const passwordConfirm = control.parent.get("passwordConfirm");

  if (!passwordNew || !passwordConfirm) {
    return null;
  }

  if (passwordConfirm.value === "") {
    return null;
  }

  if (passwordNew.value === passwordConfirm.value) {
    return null;
  }

  return { passwordsNotMatching: true };
};
