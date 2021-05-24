import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "../../../../../environments/environment";
import { TokenStorageService } from "../../../auth/token-storage.service";
import { EmployeeDTO } from "app/main/dto/Employee/EmployeeDTO";
import { AccountDTO } from "app/main/dto/Employee/AccountDTO";
import { EmployeeChatDTO } from "app/main/dto/Chat/EmployeeChatDTO";
@Injectable()
export class ManagementEmployeeService {
  httpOptions: any;
  baseURL = `${environment.base_api_url}:${environment.port}/api/admin`;
  employeesURL = this.baseURL + `/employees`;
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

  /**
   * Get Employee By Id
   *
   * @returns {Promise<any>}
   */
  public getEmployee(id: number): Promise<any> {
    return new Promise((resolve, reject) => {
      console.log("Cai quai gi day =======", this.employeesURL);
      this.httpClient.get(this.employeesURL + "/" + id, this.httpOptions).subscribe((response: any) => {
        resolve(response);
      }, reject);
    });
  }

  // API Save Employee
  public saveEmployee(employee: EmployeeDTO): Promise<EmployeeDTO> {
    return new Promise((resolve, reject) => {
      this.httpClient
        .post<EmployeeDTO>(this.employeesURL + "/" + employee.id, employee, this.httpOptions)
        .subscribe((response: any) => {
          resolve(response);
        }, reject);
    });
  }

  // API Add Employee
  public addEmployee(employee: EmployeeDTO): Promise<any> {
    return new Promise((resolve, reject) => {
      this.httpClient.post<EmployeeDTO>(this.employeesURL + "/new", employee, this.httpOptions).subscribe((response: any) => {
        resolve(response);
      }, reject);
    });
  }

  // API Add List Employee
  public addListEmployee(employees: EmployeeDTO[]): Promise<any> {
    return new Promise((resolve, reject) => {
      this.httpClient.post<EmployeeDTO>(this.employeesURL + "/new-list", employees, this.httpOptions).subscribe((response: any) => {
        resolve(response);
      }, reject);
    });
  }

  // API Reset Password
  public resetPassword(account: AccountDTO): Promise<AccountDTO> {
    return new Promise((resolve, reject) => {
      this.httpClient.post<AccountDTO>(this.baseURL + "/reset-password", account, this.httpOptions).subscribe((response: any) => {
        resolve(response);
      }, reject);
    });
  }

  // get info admin add firebase
  public getInfoAdmin(): Promise<EmployeeChatDTO> {
    return new Promise((resolve, reject) => {
      this.httpClient.get(this.employeesURL + "/admin", this.httpOptions).subscribe((response: any) => {
        resolve(response);
      }, reject);
    });
  }
}
