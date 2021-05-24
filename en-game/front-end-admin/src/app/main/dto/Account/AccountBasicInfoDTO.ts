import { EmployeeBasicInfoDTO } from '../Employee/EmployeeBasicInfoDTO';

export class AccountBasicInfoDTO {
    username: string;
    lastLogin: string;
    employee: EmployeeBasicInfoDTO;
}