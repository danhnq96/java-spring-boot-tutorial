import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { APIHttpService } from "../theme/utils/api-http.service";
import { BehaviorSubject } from "rxjs";
import { Cart, Product } from "../app.models";
import { CART } from "../theme/constants";

@Injectable({
  providedIn: "root",
})
export class CartService {
  private apiHttpService: APIHttpService;
  public carts: BehaviorSubject<Cart[]>;

  constructor(public http: HttpClient, public snackBar: MatSnackBar, public router: Router) {
    this.apiHttpService = new APIHttpService(http);
    this.carts = new BehaviorSubject<Cart[]>([]);
  }

  public addCart(userId: number, cart: Cart) {
    if (userId) {
      this.apiHttpService
        .post("order/cart/add", {
          userId,
          cart,
        })
        .subscribe(
          (res: any) => {
            if (res && res.data) {
              // const { page } = res.data;
              this.carts.next(res.data);

              const message = "The product " + cart.name + " has been added to cart.";
              const status = "success";
              this.snackBar.open(message, "×", { panelClass: [status], verticalPosition: "top", duration: 3000 });
            }
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
  }

  public getCarts(userId: number): void {
    if (userId) {
      this.apiHttpService
        .get("order/cart/", {
          params: { userId },
        })
        .subscribe(
          (res: any) => {
            if (res && res.data) {
              this.carts.next(res.data);
            }
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
  }

  public update(cart: Cart) {
    if (cart) {
      this.apiHttpService
        .put("order/cart/", {
          cart,
        })
        .subscribe(
          (res: any) => {
            if (res && res.data) {
              this.carts.next(res.data);
            }
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
  }

  public removeCart(cart: Cart): void {
    if (cart) {
      this.apiHttpService
        .delete("order/cart/remove", {
          params: { userId: cart.userId, cartId: cart.id },
        })
        .subscribe(
          (res: any) => {
            if (res && res.data) {
              // const { page } = res.data;
              this.carts.next(res.data);
            }
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
  }

  public clearAllCarts(userId: number): void {
    if (userId) {
      this.apiHttpService
        .delete("order/cart/clear_all", {
          params: { userId },
        })
        .subscribe(
          (res: any) => {
            if (res) {
              // const { page } = res.data;
              this.carts.next([]);
            }
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
  }
}
