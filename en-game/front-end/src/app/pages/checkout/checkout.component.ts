import { Component, OnInit, ViewChild } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatStepper } from "@angular/material/stepper";
import * as dayjs from "dayjs";
import { AppService } from "../../app.service";
import { Address } from "../../dto/address";
import { AuthService } from "../../services/AuthService";
import { AddressService } from "../../services/AddressService";
import { User } from "../../dto/user";
import { StripeCardElementOptions, StripeElementsOptions } from "@stripe/stripe-js";
import { StripeCardComponent, StripeService } from "ngx-stripe";
import { MatSnackBar } from "@angular/material/snack-bar";
import { switchMap } from "rxjs/operators";
import { Observable } from "rxjs";
import { environment } from "../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Order, OrderStatus, ProductDTO } from "../../dto/order";

@Component({
  selector: "app-checkout",
  templateUrl: "./checkout.component.html",
  styleUrls: ["./checkout.component.scss"],
})
export class CheckoutComponent implements OnInit {
  @ViewChild("horizontalStepper", { static: true }) horizontalStepper: MatStepper;
  @ViewChild("verticalStepper", { static: true }) verticalStepper: MatStepper;
  @ViewChild(StripeCardComponent) card: StripeCardComponent;

  billingForm: FormGroup;
  deliveryForm: FormGroup;
  paymentForm: FormGroup;
  countries = [];
  months = [];
  years = [];
  deliveryMethods = [];
  grandTotal = 0;
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

  public user: User;
  public billingAddress: Address;

  constructor(
    private http: HttpClient,
    public appService: AppService,
    public formBuilder: FormBuilder,
    public authService: AuthService,
    public addressService: AddressService,
    private stripeService: StripeService,
    private snackBar: MatSnackBar,
  ) {
    authService.user.subscribe((value) => {
      this.user = value;
      if (this.user) {
        addressService.getBillingAddress(`address/billing/user/${this.user.id}`);
      }
    });

    addressService.billingAddress.subscribe((value) => {
      this.billingAddress = value;

      if (value && this.billingForm) {
        Object.keys(value).forEach((key) => {
          if (key !== "id" && key !== "userId") {
            this.billingForm.controls[key].patchValue(value[key]);
          }
        });
      }
    });
  }

  ngOnInit() {
    this.appService.Data.cartList.forEach((product) => {
      this.grandTotal += product.cartCount * product.newPrice;
    });
    this.countries = this.appService.getCountries();
    this.months = this.appService.getMonths();
    this.years = this.appService.getYears();
    this.deliveryMethods = this.appService.getDeliveryMethods();

    this.billingForm = this.formBuilder.group({
      firstName: [this.billingAddress ? this.billingAddress.firstName : "", Validators.required],
      lastName: [this.billingAddress ? this.billingAddress.lastName : "", Validators.required],
      midName: this.billingAddress ? this.billingAddress.midName : "",
      company: this.billingAddress ? this.billingAddress.company : "",
      email: [this.billingAddress ? this.billingAddress.email : "", Validators.required],
      phone: [this.billingAddress ? this.billingAddress.phone : "", Validators.required],
      country: [this.billingAddress ? this.billingAddress.country : "", Validators.required],
      city: [this.billingAddress ? this.billingAddress.city : "", Validators.required],
      province: this.billingAddress ? this.billingAddress.province : "",
      postalCode: [this.billingAddress ? this.billingAddress.postalCode : "", Validators.required],
      address: [this.billingAddress ? this.billingAddress.address : "", Validators.required],
    });

    this.deliveryForm = this.formBuilder.group({
      deliveryMethod: [this.deliveryMethods[0], Validators.required],
    });

    this.paymentForm = this.formBuilder.group({
      cardHolderName: ["", Validators.required],
      amount: [{ value: this.grandTotal, disabled: true }, Validators.required],
    });
  }

  public placeOrder(stepper) {
    if (this.paymentForm.valid) {
      const details: ProductDTO[] = this.appService.Data.cartList.map((product) => {
        return {
          productId: product.id,
          name: product.name,
          images: product.images,
          oldPrice: product.oldPrice,
          newPrice: product.newPrice,
          ratingsCount: product.ratingsCount,
          ratingsValue: product.ratingsValue,
          description: product.description,
          availibilityCount: product.availibilityCount,
          cartCount: product.cartCount,
          color: product.color,
          size: product.size,
          weight: product.weight,
          categoryId: product.categoryId,
        } as ProductDTO;
      });
      this.createPaymentIntent({
        amount: this.paymentForm.get("amount").value,
        discount: 0.1,
        employeeId: 1,
        orderCode: dayjs(Date.now()).format("[EG]-YYYYMMDD-HHmmss"),
        orderDetails: details,
        shippingFee: 10,
        shippingId: 1,
        status: OrderStatus.PROCESSING,
        tax: 0.1,
        userId: this.user.id,
      })
        .pipe(
          switchMap((res) => {
            if (res && res.data) {
              const { orderId, clientSecret } = res.data;

              return this.stripeService.confirmCardPayment(clientSecret, {
                payment_method: {
                  card: this.card.element,
                  billing_details: {
                    name: this.paymentForm.get("cardHolderName").value,
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
            this.snackBar.open(result.error.message, "×", {
              panelClass: "error",
              verticalPosition: "top",
              duration: 3000,
            });
          } else {
            // The payment has been processed!
            if (result.paymentIntent.status === "succeeded") {
              stepper.next();
              this.horizontalStepper._steps.forEach((step) => (step.editable = false));
              this.verticalStepper._steps.forEach((step) => (step.editable = false));
              this.appService.Data.cartList.length = 0;
              this.appService.Data.totalPrice = 0;
              this.appService.Data.totalCartCount = 0;
              this.appService.clearCarts();
            }
          }
        });
    } else {
      console.log(this.paymentForm);
    }
  }

  createPaymentIntent(order: Order): Observable<any> {
    return this.http.post<any>(`${environment.apiURL}/order/checkout/payment_intents`, order);
  }
}
