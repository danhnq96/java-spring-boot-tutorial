import { Component, OnInit } from "@angular/core";
import { Data, AppService } from "../../app.service";
import { AuthService } from "../../services/AuthService";
import { CartService } from "../../services/CartService";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
  total = [];
  grandTotal = 0;
  cartItemCount = [];
  cartItemCountTotal = 0;
  constructor(public appService: AppService, public authService: AuthService, public cartService: CartService) {
    this.cartService.carts.subscribe((value) => {
      this.total = [];
      this.grandTotal = 0;
      this.cartItemCount = [];
      this.cartItemCountTotal = 0;

      value.forEach((product) => {
        this.total[product.id] = product.cartCount * product.newPrice;
        this.grandTotal += product.cartCount * product.newPrice;
        this.cartItemCount[product.id] = product.cartCount;
        this.cartItemCountTotal += product.cartCount;
      });
    });
  }

  ngOnInit() {}

  public updateCart(value) {
    if (value) {
      this.total[value.productId] = value.total;
      this.cartItemCount[value.productId] = value.soldQuantity;
      this.grandTotal = 0;
      this.total.forEach((price) => {
        this.grandTotal += price;
      });
      this.cartItemCountTotal = 0;
      this.cartItemCount.forEach((count) => {
        this.cartItemCountTotal += count;
      });

      this.appService.Data.totalPrice = this.grandTotal;
      this.appService.Data.totalCartCount = this.cartItemCountTotal;

      const cart = this.appService.Data.cartList.filter((item) => item.id === value.productId)[0];

      cart.cartCount = value.soldQuantity;
      this.appService.updateCartCount(cart);
    }
  }

  public remove(product) {
    const index: number = this.appService.Data.cartList.indexOf(product);
    if (index !== -1) {
      // this.appService.Data.cartList.splice(index, 1);
      this.grandTotal = this.grandTotal - this.total[product.id];
      this.appService.Data.totalPrice = this.grandTotal;
      this.total.forEach((val) => {
        if (val == this.total[product.id]) {
          this.total[product.id] = 0;
        }
      });

      this.cartItemCountTotal = this.cartItemCountTotal - this.cartItemCount[product.id];
      // this.appService.Data.totalCartCount = this.cartItemCountTotal;
      this.cartItemCount.forEach((val) => {
        if (val == this.cartItemCount[product.id]) {
          this.cartItemCount[product.id] = 0;
        }
      });
      this.appService.resetProductCartCount(product);
    }
  }

  public clear() {
    this.appService.clearCarts();
  }
}
