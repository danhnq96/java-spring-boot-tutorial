import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PromotionService {
  constructor(
    // tslint:disable-next-line:no-shadowed-variable
    public http: HttpClient
  ) {
  }
  public API_GET_INFO_BY_ID = 'http://localhost:8080/promotion/getPromoById';
  public API_GET_ALL_INFO = 'http://localhost:8080/promotion/getAll';
  public API_ADD_NEW = 'http://localhost:8080/promotion/addNewPromo';
  public API_UPDATE = 'http://localhost:8080/promotion/updatePromo';
  public API_DELETE = 'http://localhost:8080/promotion/deletePromo';
  public API_GET_INFO = 'http://localhost:8080/promotion/getPromo';
  public API_GET_BY_DATE = 'http://localhost:8080/promotion/getPromoByDepartureDate?promotion';
  public API_GET_BY_FLIGHT = 'http://localhost:8080/promotion/getPromoByFlight';

  getPromoInMon(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/04');
  }
  // @ts-ignore
  getPromoInTues(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/05');
  }
  getPromoInWeds(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/06');
  }
  getPromoInThurs(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/07');
  }
  getPromoInFri(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/08');
  }
  getPromoInSat(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/02');
  }
  getPromoInSun(): Observable<any> {
    return this.http.get(this.API_GET_BY_DATE + '=2021/01/03');
  }
  getPromoByFlightDAD_SGN(): Observable<any> {
    // @ts-ignore
    return this.http.get(this.API_GET_BY_FLIGHT + '/' + 'DAD-SGN');
  }
  getPromoByFlightDAD_HAN(): Observable<any> {   // @ts-ignore
    return this.http.get(this.API_GET_BY_FLIGHT + '/' + 'DAD-HAN');
  }
  getPromoByFlightSGN_DLI(): Observable<any> {
    // @ts-ignore
    return this.http.get(this.API_GET_BY_FLIGHT + '/' + 'SGN-DLI');
  }
  getAll(): Observable<any> {
    // console.log(this.http.get(this.API_GET_ALL_INFO));
    // return this.http.get(this.API);
    return this.http.get(this.API_GET_ALL_INFO);
  }
  // @ts-ignore
  getPromo(): Observable<any> {
    return this.http.get(this.API_GET_INFO);
  }
  addNewPromo(promotion): Observable<any> {
    return this.http.post(this.API_ADD_NEW, promotion);
    // return this.http.get(this.API, promotion);
  }

  getPromoById(promotionid): Observable<any> {
    return this.http.get(this.API_GET_INFO_BY_ID + '/' + promotionid);
  }
  updatePromo(promotion, promotionid): Observable<any> {
    return this.http.put(this.API_UPDATE + '/' + promotionid, promotion);
  }
  deletePromo(promotionid): Observable<any> {
    return this.http.delete(this.API_DELETE + '/' + promotionid);
  }
}
