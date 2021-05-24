import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../../../../environments/environment';
import { TokenStorageService } from '../../../auth/token-storage.service';

@Injectable()
export class ManagementEmployeesService {
    httpOptions: unknown;
    employeesURL = `${environment.base_api_url}:${environment.port}/api/admin/employees`;
    /**
     * Constructor
     *
     * @param {HttpClient} httpClient
     */
    constructor(
        private httpClient: HttpClient, private tokenStorage: TokenStorageService
    ) {
        this.httpOptions = {
            headers: new HttpHeaders({ 'Content-Type': 'application/json', 'Authorization': `Bearer ` + this.tokenStorage.getToken() })
            , 'Access-Control-Allow-Origin': '*', 'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS'
        };
    }


    /**
     * Get employees
     *
     * @returns {Promise<any>}
     */
    public getListEmployee(page: number, size: number, search: string, sort?: string[]): Promise<any> {
        search = typeof search !== undefined ? search : "";
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.employeesURL + "?page=" + page + "&size=" + size + "&search=" + search + "&sort=" + sort, this.httpOptions)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }

    public getListExportToExcel(search: string): Promise<any> {
        return new Promise((resolve, reject) => {
            this.httpClient.get(this.employeesURL + "/export?search=" + search, this.httpOptions)
                .subscribe((response: any) => {
                    resolve(response);
                }, reject);
        });
    }
}
