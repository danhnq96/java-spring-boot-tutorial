// Angular Modules
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "../../../environments/environment";

@Injectable()
export class APIHttpService {
  private readonly BASE_URL: string = environment.apiURL;

  constructor(
    // Angular Modules
    private http: HttpClient,
  ) {}

  public get(url: string, options?: any) {
    return this.http.get(`${this.BASE_URL}/${url}`, options);
  }

  public post(url: string, data: any, options?: any) {
    return this.http.post(`${this.BASE_URL}/${url}`, data, options);
  }

  public put(url: string, data: any, options?: any) {
    return this.http.put(`${this.BASE_URL}/${url}`, data, options);
  }

  public delete(url: string, options?: any) {
    return this.http.delete(`${this.BASE_URL}/${url}`, options);
  }
}
