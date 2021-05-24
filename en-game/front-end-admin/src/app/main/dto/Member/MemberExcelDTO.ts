import { MemberTypeExcelDTO } from "../MemberType/MemberTypeExcelDTO";

export interface MemberExcelDTO {
    index:number;
    email: string;
    firstName: string;
    midName: string;
    lastName: string;
    address: string;
    birthday: string;
    image: string;
    lastLogin: string;
    registerDate: string;
    gender: number;
    phone: string;
    memberType: MemberTypeExcelDTO;
    active: boolean;
}