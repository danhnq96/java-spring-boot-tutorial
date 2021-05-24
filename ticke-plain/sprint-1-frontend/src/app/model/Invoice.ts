import {AppUser} from './AppUser';

export interface Invoice {
  id: number;
  dateCreated: string;
  formCode: string;
  invoiceCode: string;
  totalMoney: string;
  appUser: AppUser;
}
