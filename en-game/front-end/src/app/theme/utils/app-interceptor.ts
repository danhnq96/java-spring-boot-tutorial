import { Injectable } from "@angular/core";
import {
  HttpEvent,
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpResponse,
  HttpErrorResponse,
} from "@angular/common/http";
import { NgxSpinnerService } from "ngx-spinner";
import { Observable, throwError } from "rxjs";
import { map, catchError, finalize } from "rxjs/operators";
import { ACCESS_TOKEN } from "../constants";

@Injectable()
export class AppInterceptor implements HttpInterceptor {
  constructor(private spinner: NgxSpinnerService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    this.spinner.show();
    const accessToken = localStorage.getItem(ACCESS_TOKEN);

    if (accessToken) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${accessToken}`,
        },
      });
    }

    return next.handle(req).pipe(
      map((event: HttpEvent<any>) => {
        // if (event instanceof HttpResponse) {
        //   this.spinner.hide();
        // }
        return event;
      }),
      catchError((error: HttpErrorResponse) => {
        const started = Date.now();
        const elapsed = Date.now() - started;
        console.log(`Request for ${req.urlWithParams} failed after ${elapsed} ms.`);
        // debugger;
        return throwError(error);
      }),
      finalize(() => {
        this.spinner.hide();
      }),
    );
  }
}
