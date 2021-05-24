import { MemberTypeDTO } from "../MemberType/MemberTypeDTO";

export interface ListMemberDTO {
    id: number;
    email: string;
    firstName: string;
    midName: string;
    lastName: string;
    registerDate: string;
    active: boolean;
    memberType: MemberTypeDTO;
}