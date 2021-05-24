import { EmployeeBasicInfoDTO } from '../../main/dto/Employee/EmployeeBasicInfoDTO';
import * as EmployeeAction from '../actions/employeeAction'

const initalState: EmployeeBasicInfoDTO = null;

export function reducer(state: EmployeeBasicInfoDTO = initalState, action: EmployeeAction.Actions): EmployeeBasicInfoDTO {
    switch (action.type) {
        case EmployeeAction.ADD_EMPLOYEE:
            return {
                ...state = action.payload
            }
        default:
            return state;

    }
}