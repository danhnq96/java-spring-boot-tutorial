import {Component, ElementRef, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LoginService} from '../../service/login.service';
import {Router} from '@angular/router';
import {SpinnerOverlayService} from '../../service/animations/spinner-overlay.service';
import {AuthService, FacebookLoginProvider, GoogleLoginProvider} from 'angularx-social-login';
import {finalize} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  user;
  message;

  constructor(public formBuilder: FormBuilder,
              public loginService: LoginService,
              public router: Router,
              public spinnerOverlayService: SpinnerOverlayService,
              private socialAuthService: AuthService,
              public el: ElementRef
  ) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.pattern('^[a-z][a-z0-9_\\.]{3,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$')]],
      password: ['', [Validators.required, Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$')]],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.spinnerOverlayService.show();
      this.user = {
        username: this.loginForm.value.email,
        password: this.loginForm.value.password
      };
      this.loginService.authenticate(this.user)
        .pipe(finalize(() => this.spinnerOverlayService.hide()))
        .subscribe(data => {
          if (data.message) {
            this.message = data.message;
            this.loginService.logout();
          } else {
            this.user = data;
            this.loginService.broadcastLoginChange(this.user);
            this.router.navigateByUrl('');
          }
        }, error => {
          this.message = 'Sai email hoặc password';
        });
    } else {
      for (const key of Object.keys(this.loginForm.controls)) {
        if (this.loginForm.controls[key].invalid) {
          const invalidControl = this.el.nativeElement.querySelector('[formControlName="' + key + '"]');
          invalidControl.focus();
          break;
        }
      }
    }
  }

  loginGoogle() {
    this.socialAuthService.signIn(GoogleLoginProvider.PROVIDER_ID).then(data => {
      this.user = {
        username: data.email,
        password: `${GoogleLoginProvider.PROVIDER_ID}`,
        fullName: data.name,
        appUser: {
          fullName: data.name,
          email: data.email,
        }
      };
      this.loginService.loginGoogle(this.user).subscribe(data => {
        this.user.role = 'User';
        this.loginService.broadcastLoginChange(this.user);
        this.router.navigateByUrl('');
      });
    });
  }

  loginFacebook() {
    this.socialAuthService.signIn(FacebookLoginProvider.PROVIDER_ID).then(data => {
      console.log(data);
    });
  }
}
