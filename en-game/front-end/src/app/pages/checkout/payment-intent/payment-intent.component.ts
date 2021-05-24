import { Component, OnInit, ViewChild, ɵConsole } from "@angular/core";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { switchMap } from "rxjs/operators";

import { StripeService, StripeCardComponent } from "ngx-stripe";
import { StripeCardElementOptions, StripeElementsOptions, PaymentIntent } from "@stripe/stripe-js";

import { environment } from "../../../../environments/environment";
import { MatSnackBar } from "@angular/material/snack-bar";

@Component({
  selector: "app-simple-payment-intent",
  templateUrl: "./payment-intent.component.html",
  styleUrls: ["./payment-intent.component.scss"],
})
export class PaymentIntentComponent implements OnInit {
  @ViewChild(StripeCardComponent) card: StripeCardComponent;

  cardOptions: StripeCardElementOptions = {
    style: {
      base: {
        iconColor: "#666EE8",
        color: "#31325F",
        fontWeight: "300",
        fontFamily: '"Helvetica Neue", Helvetica, sans-serif',
        fontSize: "18px",
        "::placeholder": {
          color: "#CFD7E0",
        },
      },
    },
  };

  elementsOptions: StripeElementsOptions = {
    locale: "en",
  };

  stripeTest: FormGroup;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private stripeService: StripeService,
    private snackBar: MatSnackBar,
  ) {}

  ngOnInit() {
    this.stripeTest = this.fb.group({
      name: ["Angular v10", [Validators.required]],
      amount: [1000, [Validators.required, Validators.pattern(/\d+/)]],
    });
  }

  pay(): void {
    if (this.stripeTest.valid) {
      this.createPaymentIntent(this.stripeTest.get("amount").value)
        .pipe(
          switchMap((res) => {
            if (res && res.data) {
              const pi = JSON.parse(res.data);

              return this.stripeService.confirmCardPayment(pi.client_secret, {
                payment_method: {
                  card: this.card.element,
                  billing_details: {
                    name: this.stripeTest.get("name").value,
                  },
                },
              });
            }

            return null;
          }),
        )
        .subscribe((result) => {
          if (result.error) {
            // Show error to your customer (e.g., insufficient funds)
            console.log(result.error.message);
            this.snackBar.open(result.error.message, "×", {
              panelClass: "error",
              verticalPosition: "top",
              duration: 3000,
            });
          } else {
            // The payment has been processed!
            if (result.paymentIntent.status === "succeeded") {
              // Show a success message to your customer
              this.snackBar.open("Your payment is successful!", "×", {
                panelClass: "success",
                verticalPosition: "top",
                duration: 3000,
              });
            }
          }
        });
    } else {
      console.log(this.stripeTest);
    }
  }

  createPaymentIntent(amount: number): Observable<any> {
    return this.http.post<any>(`${environment.apiURL}/order/checkout/payment_intents`, { amount });
  }
}
