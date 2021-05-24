import { Injectable } from "@angular/core";
import { APIHttpService } from "../theme/utils/api-http.service";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { BehaviorSubject } from "rxjs";
import { Address } from "../dto/address";

@Injectable()
export class AddressService {
  private apiHttpService: APIHttpService;
  public billingAddress: BehaviorSubject<Address>;
  public shippingAddress: BehaviorSubject<Address>;

  constructor(public http: HttpClient, public snackBar: MatSnackBar, public router: Router) {
    this.apiHttpService = new APIHttpService(http);
    this.billingAddress = new BehaviorSubject<Address>(null);
    this.shippingAddress = new BehaviorSubject<Address>(null);
  }

  public getBillingAddress(url: string): void {
    this.apiHttpService.get(url).subscribe(
      (res: any) => {
        this.billingAddress.next(res);
      },
      (error) => {
        this.snackBar.open(error.message, "×", {
          panelClass: "error",
          verticalPosition: "top",
          duration: 3000,
        });
      },
    );
  }

  public saveBillingAddress(url: string, data: any, isUpdate?: boolean): void {
    if (isUpdate) {
      this.apiHttpService.put(url, data).subscribe(
        (res: any) => {
          this.billingAddress.next(res);
          this.snackBar.open("Billing address information updated successfully!", "×", {
            panelClass: "success",
            verticalPosition: "top",
            duration: 3000,
          });
        },
        (error) => {
          this.snackBar.open(error.message, "×", {
            panelClass: "error",
            verticalPosition: "top",
            duration: 3000,
          });
        },
      );

      return;
    }

    this.apiHttpService.post(url, data).subscribe(
      (res: any) => {
        this.billingAddress.next(res);
        this.snackBar.open("Billing address information created successfully!", "×", {
          panelClass: "success",
          verticalPosition: "top",
          duration: 3000,
        });
      },
      (error) => {
        this.snackBar.open(error.message, "×", {
          panelClass: "error",
          verticalPosition: "top",
          duration: 3000,
        });
      },
    );
  }

  public getShippingAddress(url: string): void {
    this.apiHttpService.get(url).subscribe(
      (res: any) => {
        this.shippingAddress.next(res);
      },
      (error) => {
        this.snackBar.open(error.message, "×", {
          panelClass: "error",
          verticalPosition: "top",
          duration: 3000,
        });
      },
    );
  }

  public saveShippingAddress(url: string, data: any, isUpdate?: boolean): void {
    if (isUpdate) {
      this.apiHttpService.put(url, data).subscribe(
        (res: any) => {
          this.shippingAddress.next(res);
          this.snackBar.open("Shipping address information updated successfully!", "×", {
            panelClass: "success",
            verticalPosition: "top",
            duration: 3000,
          });
        },
        (error) => {
          this.snackBar.open(error.message, "×", {
            panelClass: "error",
            verticalPosition: "top",
            duration: 3000,
          });
        },
      );

      return;
    }

    this.apiHttpService.post(url, data).subscribe(
      (res: any) => {
        this.billingAddress.next(res);
        this.snackBar.open("Billing address information created successfully!", "×", {
          panelClass: "success",
          verticalPosition: "top",
          duration: 3000,
        });
      },
      (error) => {
        this.snackBar.open(error.message, "×", {
          panelClass: "error",
          verticalPosition: "top",
          duration: 3000,
        });
      },
    );
  }

  ngOnDestroy() {
    this.billingAddress.unsubscribe();
    this.shippingAddress.unsubscribe();
  }
}
