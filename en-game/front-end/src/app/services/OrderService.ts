import { Injectable } from "@angular/core";
import { APIHttpService } from "../theme/utils/api-http.service";
import { HttpClient } from "@angular/common/http";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { BehaviorSubject } from "rxjs";
import { Order, OrderRequest, CountOrderStatus } from "../dto/order";
import { environment } from "../../environments/environment";

@Injectable()
export class OrderService {
  private apiHttpService: APIHttpService;
  public orders: BehaviorSubject<Order[]>;
  public countOrderStatuses: BehaviorSubject<CountOrderStatus[]>;
  public totalPage: BehaviorSubject<number>;
  public totalElements: BehaviorSubject<number>;
  public size: BehaviorSubject<number>;

  constructor(public http: HttpClient, public snackBar: MatSnackBar, public router: Router) {
    this.apiHttpService = new APIHttpService(http);
    this.orders = new BehaviorSubject<Order[]>([]);
    this.countOrderStatuses = new BehaviorSubject<CountOrderStatus[]>([]);
    this.totalPage = new BehaviorSubject<number>(0);
    this.totalElements = new BehaviorSubject<number>(0);
    this.size = new BehaviorSubject<number>(0);
  }

  public getOrders(orderRequest: OrderRequest): void {
    this.apiHttpService
      .get(`order/`, {
        params: {
          userId: orderRequest.userId.toString(10),
          pageNumber: orderRequest.pageNumber.toString(10),
          pageSize: orderRequest.pageSize.toString(10),
        },
      })
      .subscribe(
        (res: any) => {
          if (res && res.data) {
            const { page } = res.data;

            this.orders.next(page.content);
            this.totalPage.next(page.totalPage);
            this.totalElements.next(page.totalElements);
            this.size.next(page.size);
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

  public getCountOrderStatus(userId: number): void {
    this.apiHttpService
      .get(`order/count_order_status/`, {
        params: {
          userId: userId,
        },
      })
      .subscribe(
        (res: any) => {
          if (res && res.data) {
            this.countOrderStatuses.next(res.data);
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
