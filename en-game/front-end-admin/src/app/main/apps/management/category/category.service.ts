import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { TokenStorageService } from "app/main/auth/token-storage.service";
import { CategoryDTO } from "app/main/dto/Category/CategoryDTO";
import { environment } from "environments/environment";


@Injectable()
export class ManagementCategoryService {
    httpOptions: any;
    baseURL = `${environment.base_api_url}:${environment.port}/api/admin`;
    categoryURL = this.baseURL + `/categories`;
    routeParams: any;

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

    /**
      * Get Category By Id
      *
      * @returns {Promise<any>}
      */
    public getCategory(id: number): Promise<any> {
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.categoryURL + "/" + id, this.httpOptions).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }

    // // API Save Category
    // public saveCategory(category: CategoryDTO): Promise<CategoryDTO> {
    //     return new Promise((resolve, reject) => {
    //         this.httpClient
    //             .post<CategoryDTO>(this.categoryURL + "/" + category.id, category, this.httpOptions)
    //             .subscribe((response: any) => {
    //                 resolve(response);
    //             }, reject);
    //     });
    // }

    // API Save Category
    public saveCategory(category: CategoryDTO): Promise<CategoryDTO> {
        return new Promise((resolve, reject) => {
            this.httpClient.post<CategoryDTO>(this.categoryURL + "/save", category, this.httpOptions).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }

}