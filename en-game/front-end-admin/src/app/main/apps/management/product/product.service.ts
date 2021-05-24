import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { TokenStorageService } from "app/main/auth/token-storage.service";
import { ColorDTO } from "app/main/dto/Color/colorDTO";
import { UploadImageMainDTO } from "app/main/dto/image/UploadImageMainDTO";
import { ProductDTO } from "app/main/dto/Product/ProductDTO";
import { environment } from "environments/environment";


@Injectable()
export class ManagementProductService {
    httpOptions: any;
    baseURL = `${environment.base_api_url}:${environment.port}/api/admin`;
    productURL = this.baseURL + `/products`;
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
      * Get Product By Id
      *
      * @returns {Promise<any>}
      */
    public getProduct(id: number): Promise<any> {
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.productURL + "/" + id, this.httpOptions).subscribe((response: any) => {
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

    // API Save Product
    public saveProduct(product: ProductDTO): Promise<ProductDTO> {
        return new Promise((resolve, reject) => {
            this.httpClient.post<ProductDTO>(this.productURL + "/save", product, this.httpOptions).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }


    // API Save Product
    public getListCategories(): Promise<any> {
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.productURL + "/categories", this.httpOptions).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }

    // API Upload Image Product
    public uploadImageMain(uploadImageMain: UploadImageMainDTO): Promise<any> {
        return new Promise((resolve, reject) => {
            this.httpClient.post<ProductDTO>(this.productURL + "/uploadImageMain", uploadImageMain, this.httpOptions).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }

    // API Delete Color
    public deleteColor(color: ColorDTO): Promise<any> {
        return new Promise((resolve, reject) => {
            this.httpClient.post<ProductDTO>(this.productURL + "/deleteColor", color, this.httpOptions).subscribe((response: any) => {
                resolve(response);
            }, reject);
        });
    }
}