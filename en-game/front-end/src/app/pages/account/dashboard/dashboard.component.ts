import { Component, OnInit } from "@angular/core";
import { OrderService } from "../../../services/OrderService";
import { AuthService } from "../../../services/AuthService";
import { User } from "../../../dto/user";
import { CountOrderStatus, OrderStatus } from "../../../dto/order";

@Component({
  selector: "app-dashboard",
  templateUrl: "./dashboard.component.html",
  styleUrls: ["./dashboard.component.scss"],
})
export class DashboardComponent implements OnInit {
  public user: User;
  public countOrderStatuses: CountOrderStatus[];

  constructor(public authService: AuthService, public orderService: OrderService) {
    authService.user.subscribe((value) => {
      this.user = value;
    });

    orderService.countOrderStatuses.subscribe((value) => {
      this.countOrderStatuses = value;
    });
  }

  ngOnInit() {
    this.orderService.getCountOrderStatus(this.user.id);
  }

  public get ORDER_STATUS() {
    return OrderStatus;
  }

  public getOrderStatusPercent(orderStatus: OrderStatus) {
    const total = this.countOrderStatuses.reduce((acc, val) => (acc += val.count), 0);
    let statusCount = 0;

    for (let i = 0; i < this.countOrderStatuses.length; i++) {
      // @ts-ignore
      if (this.countOrderStatuses[i].status === OrderStatus[orderStatus]) {
        statusCount = this.countOrderStatuses[i].count;
        break;
      }
    }

    return isNaN((statusCount * 100) / total) ? (0).toFixed(2) : ((statusCount * 100) / total).toFixed(2);
  }
}
