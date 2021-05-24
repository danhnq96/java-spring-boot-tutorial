import { AccountBasicInfoDTO } from '../dto/Account/AccountBasicInfoDTO';

export class JwtResponse {
    token: string;
    username:string;
    accountBasicInfoDTO: AccountBasicInfoDTO
}