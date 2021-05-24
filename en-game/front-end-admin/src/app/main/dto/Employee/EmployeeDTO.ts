import { AccountDTO } from './AccountDTO';

export class EmployeeDTO {
    constructor(){}
    id: number;
    firstName: string;
    midName: string;
    lastName: string;
    address: string;
    birthday: string;
    image: string;
    active: boolean;
    startDate: string;
    gender: number;
    email: string;
    idCard: string;
    phone: string;
    account : AccountDTO = new AccountDTO();
}