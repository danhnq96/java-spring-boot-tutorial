import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, timer} from 'rxjs';
import {AbstractControl, AsyncValidatorFn} from '@angular/forms';
import {map, switchMap} from 'rxjs/operators';
import {Employee} from '../../model/employee/employee.class';

const URL = 'http://localhost:8080/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  public readonly API: string = 'http://localhost:8080/employee';
  public readonly API_SEND_EMAIL = 'http://localhost:8080/sendEmail';
  public readonly API_CHANGE_PASSWORD = 'http://localhost:8080/changePassword';
  public readonly API_FIND_ID = 'http://localhost:8080/employee';

  constructor(
    public http: HttpClient
  ) {
  }

  /*
     Create by : Quoc_NT
     function : send Token Code Random to Email Employee when employee change password
     */
  findEmployeeByIdAccount(idFind: any): Observable<any> {
    return this.http.get(this.API_FIND_ID + '/findEmployee/' + idFind);
  }



  sendToEmailEmployee(id: number): Observable<any> {
    console.log(this.API_SEND_EMAIL + '/' + id);
    return this.http.get(this.API_SEND_EMAIL + '/' + id);
  }

  /*
  Create by : Quoc_NT
  function : change Password for employee
*/
  changePasswordEmployee(id: number, newPassword) {
    console.log(this.API_CHANGE_PASSWORD + '/' + id, newPassword);
    return this.http.put(this.API_CHANGE_PASSWORD + '/' + id, newPassword);
  }

  // Create by: Mai_HTQ
  getAllEmployeeService(): Observable<any> {
    return this.http.get(this.API + '/list');
  }

  getRole(accountId): Observable<any> {
    return this.http.get(this.API + '/find-role/' + accountId);
  }

  findEmployeeByIdService(idFind: any): Observable<any> {
    return this.http.get(this.API + '/findEmployeeById/' + idFind);
  }

  createNewEmployeeService(employeeDTO: Employee): Observable<any> {
    return this.http.post(this.API + '/createNew', employeeDTO);
  }

  editEmployeeService(employeeDTO, idNeedEdit): Observable<any> {
    return this.http.put(this.API + '/editEmployee/' + idNeedEdit, employeeDTO);
  }

  deleteEmployeeService(idDelete: any): Observable<any> {
    return this.http.delete(this.API + '/delete/' + idDelete);
  }

  // --------------------------- Search ----------------------------------
  searchEmployeeFullNameService(idSearch: any): Observable<any> {
    return this.http.get(this.API + '/searchFullName/' + idSearch);
  }

  searchEmployeePhoneNumberService(idSearch: any): Observable<any> {
    return this.http.get(this.API + '/searchPhoneNumber/' + idSearch);
  }

  searchEmployeeEmailService(idSearch: any): Observable<any> {
    return this.http.get(this.API + '/searchEmail/' + idSearch);
  }
  // ------------------------------- Validate ----------------------------
  validateEmployeeCode(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<{ [key: string]: any } | null> => {
      return this.search(control.value)
        .pipe(
          map(res => {
            if (res.length) {
              return {employeeCodeExists: true};
            }
          })
        );
    };
  }

  search(text) {
    console.log(text);
    return timer(100)
      .pipe(
        switchMap(() => {
          return this.http.get<any>(`${URL}/findEmployeeByCode?employeeCode=${text}`);
        })
      );
  }

  validateWhiteSpace(control: AbstractControl) {
    if (control.value !== '') {
      const isWhiteSpace = control.value.trim().length === 0;
      if (isWhiteSpace) {
        const isValid = !isWhiteSpace;
        return isValid ? null : {whiteSpace: true};
      }
    }
  }

  validateSpecialCharacter(control: AbstractControl) {
    const specialCharacter = '[~`!@#$%^&*()-+=/*?:;.,|]+';
    return (control.value.match(specialCharacter)) ? {
      specialCharacter: true
    } : null;
  }

  validateEmployeeEmail(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<{ [key: string]: any } | null> => {
      return this.searchEmail(control.value)
        .pipe(
          map(res => {
            if (res.length) {
              return {emailExists: true};
            }
          })
        );
    };
  }

  searchEmail(text) {
    console.log(text);
    return timer(100)
      .pipe(
        switchMap(() => {
          return this.http.get<any>(`${URL}/findEmployeeByEmail?email=${text}`);
        })
      );
  }
}
