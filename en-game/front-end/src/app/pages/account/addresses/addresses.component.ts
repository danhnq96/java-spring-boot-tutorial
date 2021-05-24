import { Component, OnInit } from "@angular/core";
import { MatSnackBar } from "@angular/material/snack-bar";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { AppService } from "../../../app.service";
import { AuthService } from "../../../services/AuthService";
import { AddressService } from "../../../services/AddressService";
import { User } from "../../../dto/user";
import { Address } from "../../../dto/address";

@Component({
  selector: "app-addresses",
  templateUrl: "./addresses.component.html",
  styleUrls: ["./addresses.component.scss"],
})
export class AddressesComponent implements OnInit {
  billingForm: FormGroup;
  shippingForm: FormGroup;
  countries = [];
  public user: User;
  public billingAddress: Address;
  public shippingAddress: Address;

  constructor(
    public appService: AppService,
    public formBuilder: FormBuilder,
    public snackBar: MatSnackBar,
    public authService: AuthService,
    public addressService: AddressService,
  ) {
    authService.user.subscribe((value) => {
      this.user = value;
      if (this.user) {
        addressService.getBillingAddress(`address/billing/user/${this.user.id}`);
        addressService.getShippingAddress(`address/shipping/user/${this.user.id}`);
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

    addressService.shippingAddress.subscribe((value) => {
      this.shippingAddress = value;

      if (value && this.billingForm) {
        Object.keys(value).forEach((key) => {
          if (key !== "id" && key !== "userId") {
            this.shippingForm.controls[key].patchValue(value[key]);
          }
        });
      }
    });
  }

  ngOnInit() {
    this.countries = this.appService.getCountries();
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
    this.shippingForm = this.formBuilder.group({
      firstName: [this.shippingAddress ? this.shippingAddress.firstName : "", Validators.required],
      lastName: [this.shippingAddress ? this.shippingAddress.lastName : "", Validators.required],
      midName: this.shippingAddress ? this.shippingAddress.midName : "",
      company: this.shippingAddress ? this.shippingAddress.company : "",
      email: [this.shippingAddress ? this.shippingAddress.email : "", Validators.required],
      phone: [this.shippingAddress ? this.shippingAddress.phone : "", Validators.required],
      country: [this.shippingAddress ? this.shippingAddress.country : "", Validators.required],
      city: [this.shippingAddress ? this.shippingAddress.city : "", Validators.required],
      province: this.shippingAddress ? this.shippingAddress.province : "",
      postalCode: [this.shippingAddress ? this.shippingAddress.postalCode : "", Validators.required],
      address: [this.shippingAddress ? this.shippingAddress.address : "", Validators.required],
    });
  }

  public onBillingFormSubmit(value: Address): void {
    if (this.billingForm.valid) {
      this.addressService.saveBillingAddress(
        "address/billing/user",
        {
          id: this.billingAddress ? this.billingAddress.id : undefined,
          userId: this.user.id,
          firstName: value.firstName,
          lastName: value.lastName,
          midName: value.midName,
          company: value.midName,
          email: value.email,
          phone: value.phone,
          country: value.country,
          city: value.city,
          province: value.province,
          postalCode: value.postalCode,
          address: value.address,
        },
        !!this.billingAddress,
      );
    }
  }

  public onShippingFormSubmit(value: Address): void {
    if (this.shippingForm.valid) {
      this.addressService.saveShippingAddress(
        "address/shipping/user",
        {
          id: this.shippingAddress ? this.shippingAddress.id : undefined,
          userId: this.user.id,
          firstName: value.firstName,
          lastName: value.lastName,
          midName: value.midName,
          company: value.midName,
          email: value.email,
          phone: value.phone,
          country: value.country,
          city: value.city,
          province: value.province,
          postalCode: value.postalCode,
          address: value.address,
        },
        !!this.shippingAddress,
      );
    }
  }
}
