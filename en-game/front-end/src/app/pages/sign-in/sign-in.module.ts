import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { ReactiveFormsModule } from "@angular/forms";
import { RecaptchaModule, RecaptchaFormsModule } from "ng-recaptcha";
import { SharedModule } from "../../shared/shared.module";
import { SignInComponent } from "./sign-in.component";

export const routes = [{ path: "", component: SignInComponent, pathMatch: "full" }];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    SharedModule,
    RecaptchaModule, //this is the recaptcha main module
    RecaptchaFormsModule, //this is the module for form in case form validation
  ],
  declarations: [SignInComponent],
})
export class SignInModule {}
