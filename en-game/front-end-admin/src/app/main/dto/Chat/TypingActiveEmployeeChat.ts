export class TypingActiveEmployeeChat {
    constructor(){
        this.unread = 0;
        this.typing = false;
        this.active = false;
        this.seen = false;
    }
    unread: number;
    typing: boolean;
    active: boolean;
    seen: boolean;
}