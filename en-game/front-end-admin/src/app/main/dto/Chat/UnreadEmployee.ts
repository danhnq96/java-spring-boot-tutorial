import { EmployeeChatDTO } from "./EmployeeChatDTO";

export class UnreadEmployee extends EmployeeChatDTO {
    id: string;
    unread: number;
}