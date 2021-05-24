import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { FormBuilder, FormGroup, FormControl, Validators, FormGroupDirective } from "@angular/forms";
import { MatSnackBar } from "@angular/material/snack-bar";
// import { ReCaptchaV3Service } from "ng-recaptcha";
import { emailValidator, matchingPasswords } from "../../theme/utils/app-validators";
import { SignUp, SignIn, ROLE } from "../../dto/signup";
import { AuthService } from "../../services/AuthService";
import { environment } from "../../../environments/environment";

@Component({
  selector: "app-sign-in",
  templateUrl: "./sign-in.component.html",
  styleUrls: ["./sign-in.component.scss"],
})
export class SignInComponent implements OnInit {
  loginForm: FormGroup;
  registerForm: FormGroup;
  reCaptchaSiteKey: string;
  reCaptchaResponse: string;
  isLoginReCaptchaChecked: boolean;
  isRegisterReCaptchaChecked: boolean;

  constructor(
    public formBuilder: FormBuilder,
    public router: Router,
    public snackBar: MatSnackBar,
    public authService: AuthService, // private recaptchaV3Service: ReCaptchaV3Service,
  ) {}

  ngOnInit() {
    const urlParams = new URLSearchParams(window.location.search);
    const accessToken = urlParams.get("token");

    if (accessToken) {
      this.authService.socialLogin(accessToken);
    }

    this.loginForm = this.formBuilder.group({
      email: ["", Validators.compose([Validators.required, emailValidator])],
      password: ["", Validators.compose([Validators.required, Validators.minLength(6)])],
      recaptchaReactive: new FormControl(null, Validators.required),
    });

    this.registerForm = this.formBuilder.group(
      {
        name: ["", Validators.compose([Validators.required, Validators.minLength(3)])],
        email: ["", Validators.compose([Validators.required, emailValidator])],
        password: ["", Validators.required],
        confirmPassword: ["", Validators.required],
        recaptchaReactive: new FormControl(null, Validators.required),
      },
      { validator: matchingPasswords("password", "confirmPassword") },
    );

    this.reCaptchaSiteKey = environment.reCAPTCHASiteKey;
    this.isLoginReCaptchaChecked = false;
    this.isRegisterReCaptchaChecked = false;
  }

  public onLoginFormSubmit(values: SignIn): void {
    if (this.loginForm.valid) {
      this.authService.login("auth/login", {
        email: values.email,
        password: values.password,
        reCaptchaResponse: this.reCaptchaResponse,
      });
    }
  }

  public onRegisterFormSubmit(values: SignUp, formDirective: FormGroupDirective): void {
    if (this.registerForm.valid) {
      this.authService
        .signup("auth/signup", {
          name: values.name,
          email: values.email,
          password: values.password,
          role: [ROLE.USER],
        })
        .subscribe(
          (response) => {
            this.snackBar.open("Register successfully and activation code was send to your email!", "×", {
              panelClass: "success",
              verticalPosition: "top",
              duration: 3000,
            });

            formDirective.resetForm();
            this.registerForm.reset();
          },
          (error) => {
            this.snackBar.open(error.error.message, "×", {
              panelClass: "error",
              verticalPosition: "top",
              duration: 5000,
            });
          },
        );
    }
  }

  public onRegisterBySocial(providerName: string): void {
    window.location.href = `http://localhost:8762/api/oauth2/authorize/${providerName}?redirect_uri=http://localhost:4200/sign-in`;
  }

  // public executeImportantAction(): void {
  //   this.recaptchaV3Service.execute("importantAction").subscribe((token) => {
  //     // this.handleToken(token)
  //     console.log("Capcha token gi day =====", token);
  //   });
  // }

  resolved(captchaResponse: string, isRegister?: boolean) {
    this.reCaptchaResponse = captchaResponse;

    if (isRegister) {
      this.isRegisterReCaptchaChecked = captchaResponse && captchaResponse !== "";
      return;
    }

    this.isLoginReCaptchaChecked = captchaResponse && captchaResponse !== "";
  }
}
