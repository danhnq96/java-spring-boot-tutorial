import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { ReactiveFormsModule } from "@angular/forms";
import { SharedModule } from "../../shared/shared.module";
import { CheckoutComponent } from "./checkout.component";
import { AddressService } from "../../services/AddressService";
import { PaymentIntentComponent } from "./payment-intent/payment-intent.component";
import { NgxStripeModule, StripeService } from "ngx-stripe";
import { environment } from "../../../environments/environment";

export const routes = [
  { path: "", component: CheckoutComponent, pathMatch: "full" },
  { path: "payment-intent", component: PaymentIntentComponent, data: { breadcrumb: "Payment" } },
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
    ReactiveFormsModule,
    SharedModule,
    NgxStripeModule.forChild(environment.stripePublicKey),
  ],
  declarations: [CheckoutComponent, PaymentIntentComponent],
  providers: [AddressService, StripeService],
})
export class CheckoutModule {}
