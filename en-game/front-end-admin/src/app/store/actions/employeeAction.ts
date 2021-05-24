import { Action } from '@ngrx/store';
import { EmployeeBasicInfoDTO } from 'app/main/dto/Employee/EmployeeBasicInfoDTO';


export const ADD_EMPLOYEE = "ADD"

export class AddEmployee implements Action {
            readonly type = ADD_EMPLOYEE
            constructor(public payload: EmployeeBasicInfoDTO){}
}

export type Actions = AddEmployee