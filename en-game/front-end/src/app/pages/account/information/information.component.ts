import { Component, OnInit } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
import { emailValidator, matchingPasswords } from "../../../theme/utils/app-validators";
import { AuthService } from "../../../services/AuthService";
import { UpdateUser } from "../../../dto/signup";
import { User } from "../../../dto/user";

@Component({
  selector: "app-information",
  templateUrl: "./information.component.html",
  styleUrls: ["./information.component.scss"],
})
export class InformationComponent implements OnInit {
  infoForm: FormGroup;
  passwordForm: FormGroup;
  public user: User;

  constructor(public formBuilder: FormBuilder, public snackBar: MatSnackBar, public authService: AuthService) {
    authService.user.subscribe((value) => {
      this.user = value;
    });
  }

  ngOnInit() {
    this.infoForm = this.formBuilder.group({
      firstName: [this.user ? this.user.name : "", Validators.compose([Validators.required, Validators.minLength(3)])],
      // lastName: ["", Validators.compose([Validators.required, Validators.minLength(3)])],
      email: [this.user ? this.user.email : "", Validators.compose([Validators.required, emailValidator])],
    });

    this.passwordForm = this.formBuilder.group(
      {
        currentPassword: ["", Validators.required],
        newPassword: ["", Validators.required],
        confirmNewPassword: ["", Validators.required],
      },
      { validator: matchingPasswords("newPassword", "confirmNewPassword") },
    );
  }

  public onInfoFormSubmit(values: UpdateUser): void {
    if (this.infoForm.valid) {
      this.authService.updateUserInfo("user/me", {
        name: values.firstName,
        email: values.email,
      });
    }
  }

  public onPasswordFormSubmit(values: Object): void {
    if (this.passwordForm.valid) {
      this.snackBar.open("Your password changed successfully!", "×", {
        panelClass: "success",
        verticalPosition: "top",
        duration: 3000,
      });
    }
  }
}
