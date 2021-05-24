import { AccountDTO } from './AccountDTO';

export interface ListEmployeeDTO {
    id: number;
    firstName: string;
    midName: string;
    lastName: string;
    address: string;
    birthday: string;
    image: string;
    active: boolean;
    phone: string;
    account: AccountDTO;
}