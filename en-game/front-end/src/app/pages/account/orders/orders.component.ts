import { Component, OnInit } from "@angular/core";
import { Order, OrderStatus } from "../../../dto/order";
import { Observable } from "rxjs";
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { AuthService } from "../../../services/AuthService";
import { User } from "../../../dto/user";
import { OrderService } from "../../../services/OrderService";

@Component({
  selector: "app-orders",
  templateUrl: "./orders.component.html",
  styleUrls: ["./orders.component.scss"],
})
export class OrdersComponent implements OnInit {
  // public orders: Order[] = [
  //   { number: "#3258", date: "March 29, 2018", status: "Completed", total: "$140.00 for 2 items", invoice: true },
  //   { number: "#3145", date: "February 14, 2018", status: "On hold", total: "$255.99 for 1 item", invoice: false },
  //   { number: "#2972", date: "January 7, 2018", status: "Processing", total: "$255.99 for 1 item", invoice: true },
  //   { number: "#2971", date: "January 5, 2018", status: "Completed", total: "$73.00 for 1 item", invoice: true },
  //   {
  //     number: "#1981",
  //     date: "December 24, 2017",
  //     status: "Pending Payment",
  //     total: "$285.00 for 2 items",
  //     invoice: false,
  //   },
  //   { number: "#1781", date: "September 3, 2017", status: "Refunded", total: "$49.00 for 2 items", invoice: false },
  // ];

  public orders: Order[];
  public user: User;

  constructor(private http: HttpClient, public authService: AuthService, public orderService: OrderService) {
    authService.user.subscribe((value) => {
      this.user = value;
    });

    orderService.orders.subscribe((value) => {
      this.orders = value;
    });
  }

  ngOnInit() {
    this.orderService.getOrders({ userId: this.user.id, pageNumber: 0, pageSize: 10 });
  }

  public countItemsPerOrder(order: Order): number {
    return order.orderDetails.reduce((acc, val) => (acc += val.cartCount), 0);
  }

  public get ORDER_STATUS() {
    return OrderStatus;
  }
}
