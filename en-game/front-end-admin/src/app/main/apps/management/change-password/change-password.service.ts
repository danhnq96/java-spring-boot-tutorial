import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { TokenStorageService } from "app/main/auth/token-storage.service";
import { environment } from "environments/environment";

@Injectable()
export class ManagementChangePasswordService {
    httpOptions: any;
    baseURL = `${environment.base_api_url}:${environment.port}/api/admin`;
    passwordURL = this.baseURL + `/change-password`;
    routeParams: any;
    // product: unknown;
    // onProductChanged: BehaviorSubject<unknown>;

    /**
     * Constructor
     *
     * @param {HttpClient} httpClient
     */
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


    // API Change Password
    public changePassword(password: string[]): Promise<string> {
        return new Promise((resolve, reject) => {
            this.httpClient
                .post<string>(this.passwordURL, password, this.httpOptions)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }
}
