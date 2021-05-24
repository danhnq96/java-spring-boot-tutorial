import { ChatContentDTO } from "./ChatContentDTO";

export class Chat {
    constructor() { }
    id: string;
    dialog: ChatContentDTO[];
}