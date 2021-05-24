import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../environments/environment';
import { TokenStorageService } from '../../../main/auth/token-storage.service';
@Injectable()
export class ToolBarService {
    httpOptions: unknown;
    employeesURL = `${environment.base_api_url}:${environment.port}/employees/profile`;
    /**
     * Constructor
     *
     * @param {HttpClient} httpClient
     */
    constructor(
        private httpClient: HttpClient, private tokenStorage: TokenStorageService
    ) {
        this.httpOptions = {
            headers: new HttpHeaders({
                "Content-Type": "application/json",
                Authorization: `Bearer ` + this.tokenStorage.getToken(),
            }),
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Methods": "GET,PUT,POST,DELETE,PATCH,OPTIONS",
        };
    }


    /**
     * Get Id By Username
     */
    public getProfile(): Promise<string> {
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.employeesURL, this.httpOptions)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }
}
