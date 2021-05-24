import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { TokenStorageService } from '../../../auth/token-storage.service';


@Injectable()
export class ManagementCategoriesService {
    httpOptions: any;
    baseURL = `${environment.base_api_url}:${environment.port}/api/admin`;
    categoryURL = this.baseURL + `/categories`;

    constructor(private httpClient: HttpClient, private tokenStorage: TokenStorageService) {
        this.httpOptions = {
          headers: new HttpHeaders({
            "Content-Type": "application/json",
            Authorization: `Bearer ` + this.tokenStorage.getToken(),
          }),
          "Access-Control-Allow-Origin": "http://localhost:4201",
          "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
        };
      }

      public getListCategories(page: number, size: number, search: string, sort?: string[]): Promise<any> {
        search = typeof search !== undefined ? search : "";
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.categoryURL + "?page=" + page + "&size=" + size + "&search=" + search + "&sort=" + sort, this.httpOptions)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }

    public getListExportToExcel(search: string): Promise<any> {
      return new Promise((resolve, reject) => {
          this.httpClient.get(this.categoryURL + "/export?search=" + search, this.httpOptions)
              .subscribe((response: any) => {
                  resolve(response);
              }, reject);
      });
  }
}