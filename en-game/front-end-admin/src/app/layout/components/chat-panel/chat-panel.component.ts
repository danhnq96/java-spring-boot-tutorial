import { AfterViewInit, Component, ElementRef, OnDestroy, OnInit, QueryList, ViewChild, ViewChildren, ViewEncapsulation } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Notification } from "./../../../main/commons/Notification";
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { v4 as uuidv4 } from 'uuid';
import Notiflix from "notiflix";
import { FuseSidebarService } from '@fuse/components/sidebar/sidebar.service';
import { FusePerfectScrollbarDirective } from '@fuse/directives/fuse-perfect-scrollbar/fuse-perfect-scrollbar.directive';
import { ChatPanelService } from 'app/layout/components/chat-panel/chat-panel.service';
import { AngularFireDatabase } from '@angular/fire/database';
import { TokenStorageService } from 'app/main/auth/token-storage.service';
import { Chat } from 'app/main/dto/Chat/Chat';
import { UnreadEmployee } from 'app/main/dto/Chat/UnreadEmployee';
import { TypingActiveEmployeeChat } from 'app/main/dto/Chat/TypingActiveEmployeeChat';
import * as $ from 'jquery';
import { EmployeeChatDTO } from 'app/main/dto/Chat/EmployeeChatDTO';
import { TempMessageEmployeeDTO } from 'app/main/dto/Chat/TempMessageEmployeeDTO';
import { SeenDTO } from 'app/main/dto/Chat/SeenDTO';
import { ChatContentDTO } from 'app/main/dto/Chat/ChatContentDTO';
@Component({
    selector: 'chat-panel',
    templateUrl: './chat-panel.component.html',
    styleUrls: ['./chat-panel.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ChatPanelComponent implements OnInit, AfterViewInit, OnDestroy {
    chat: Chat = new Chat();
    selectedContact: any;
    sidebarFolded: boolean;
    user: any;
    chats: Chat[] = [];
    contacts: UnreadEmployee[] = [];
    contactIdLast: string = "";
    timerFade: NodeJS.Timeout;
    lastIndex: number = -1;
    tempMessages: TempMessageEmployeeDTO[] = [];
    tempMess: string = "";
    seens: SeenDTO[] = [];
    txtSearch: string = "";
    tempContacts: UnreadEmployee[] = [];
    checkShowSearch: boolean = true;
    @ViewChild('replyForm')
    private replyForm: NgForm;
    @ViewChild('replyInput')
    private _replyInput: ElementRef;

    @ViewChildren(FusePerfectScrollbarDirective)
    private fusePerfectScrollbarDirectives: QueryList<FusePerfectScrollbarDirective>;

    // Private
    private chatViewScrollbar: FusePerfectScrollbarDirective;
    private unsubscribeAll: Subject<any>;

    /**
     * Constructor
     *
     * @param {ChatPanelService} chatPanelService
     * @param {HttpClient} _httpClient
     * @param {FuseSidebarService} fuseSidebarService
     */
    constructor(
        private chatPanelService: ChatPanelService,
        private _httpClient: HttpClient,
        private fuseSidebarService: FuseSidebarService,
        private firebaseDB: AngularFireDatabase,
        private token: TokenStorageService,
    ) {
        // Set the defaults
        this.selectedContact = null;
        this.sidebarFolded = true;

        // Set the private defaults
        this.unsubscribeAll = new Subject();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Lifecycle hooks
    // -----------------------------------------------------------------------------------------------------

    /**
     * On init
     */
    ngOnInit(): void {
        if (this.token.getToken()) {
            Notification.showWaiting();
            this.updateRealTimeAddUser();
            this.setAllFalseActiveChat();
            // Subscribe to the foldedChanged observable
            this.fuseSidebarService.getSidebar('chatPanel').foldedChanged
                .pipe(takeUntil(this.unsubscribeAll))
                .subscribe((folded) => {
                    this.sidebarFolded = folded;
                });
        }
    }

    /**
     * After view init
     */
    ngAfterViewInit(): void {
        this.chatViewScrollbar = this.fusePerfectScrollbarDirectives.find((directive) => {
            return directive.elementRef.nativeElement.id === 'messages';
        });
    }

    /**
     * On destroy
     */
    ngOnDestroy(): void {
        // Unsubscribe from all subscriptions
        this.unsubscribeAll.next();
        this.unsubscribeAll.complete();
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Private methods
    // -----------------------------------------------------------------------------------------------------
    private setTypingListenerFireBase(element: EmployeeChatDTO): void {
        this.firebaseDB.database.ref(`counts/${element.id}/${this.token.getUsername()}/typing`).on('value', response => {
            if (response.val()) {
                this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${element.id}/active`).once('value', res => {
                    if (res.val()) {
                        this.fading(true);
                    } else {
                        this.fading(false);
                    }
                });
            } else {
                this.fading(false);
            }
        });

        this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${element.id}/active`).on('value', response => {
            if (response.val()) {
                this.firebaseDB.database.ref(`counts/${element.id}/${this.token.getUsername()}/typing`).once('value', res => {
                    if (res.val()) {
                        this.fading(true);
                    } else {
                        this.fading(false);
                    }
                });
            } else {
                this.fading(false);
            }
        });
    }

    private setSeenChat(id: string): void {
        this.firebaseDB.database.ref(`counts/${id}/${this.token.getUsername()}/seen`).on('value', response => {
            if (this.token.getUsername() !== id) {
                const seenChat: SeenDTO = new SeenDTO();
                seenChat.id = id;
                seenChat.seen = response.val();
                const ind = this.seens.findIndex(e => e.id === id);
                if (ind === -1) {
                    this.seens.push(seenChat);
                } else {
                    this.seens[ind] = seenChat;
                }
            }
        });

        this.firebaseDB.database.ref(`counts/${id}/${this.token.getUsername()}`).on('value', response => {
            if (this.token.getUsername() !== id) {
                if (response.val().active || response.val().unread === 0) {
                    let typingActive: TypingActiveEmployeeChat = new TypingActiveEmployeeChat();
                    typingActive = response.val();
                    typingActive.seen = true;
                    this.firebaseDB.database.ref(`counts/${id}/${this.token.getUsername()}`).set(typingActive);
                }
            }
        });
    }


    private updateRealTimeUser(): void {
        this.firebaseDB.database.ref('users/').once('value', resp => {
            this.snapshotToArray(resp).forEach(element => {
                const empChat: UnreadEmployee = new UnreadEmployee();
                this.setTypingListenerFireBase(element);
                this.setSeenChat(element.id);
                this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${element.id}/unread`).on('value', response => {
                    if (this.token.getUsername() !== element.id) {
                        const index = this.contacts.findIndex(e => e.id === empChat.id);
                        if (index === -1) {
                            empChat.id = element.id;
                            empChat.name = element.name;
                            empChat.avatar = element.avatar;
                            empChat.status = element.status;
                            empChat.unread = response.val();
                            this.contacts.push(empChat);
                        } else {
                            this.contacts[index].unread = response.val();
                        }
                    } else {
                        empChat.id = element.id;
                        empChat.name = element.name;
                        empChat.avatar = element.avatar;
                        empChat.status = element.status;
                        this.user = empChat;
                        this.contacts.push(empChat);
                    }
                    const ind: number = this.contacts.findIndex(e => e.id === this.token.getUsername());
                    if (ind !== -1) {
                        this.contacts.splice(ind, 1);
                    }
                    this.tempContacts = this.contacts;
                });
                this.firebaseDB.database.ref(`users/${element.id}/status`).on('value', response => {
                    // eslint-disable-next-line no-shadow
                    if (element.id !== this.token.getUsername()) {
                        const index: number = this.contacts.findIndex(e => e.id === element.id);
                        if (index !== -1) {
                            this.contacts[index].status = response.val();
                        }
                    }
                });
            })

        });

    }

    private updateRealTimeAddUser(): void {
        this.firebaseDB.database.ref(`counts/users`).on('value', () => {
            this.contacts = [];
            this.updateRealTimeUser();
            this.getAllChatContents();
        });
    }

    public setAllFalseActiveChat(): void {
        this.firebaseDB.database.ref(`counts/${this.token.getUsername()}`).once('value', res => {
            this.snapshotToArray(res).forEach(e => {
                const typingActive: TypingActiveEmployeeChat = new TypingActiveEmployeeChat();
                typingActive.unread = e.unread;
                typingActive.seen = e.seen;
                this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${e.key}`).set(typingActive);
            });
            Notification.clearWaitNoMessage();
        });
        this.contactIdLast = "";
        this.checkShowSearch = true;
        this.contacts = this.tempContacts;
    }

    public typing(content: string): void {
        this.changeTypingChat(content !== "");
    }

    private changeTypingChat(check: boolean) {
        const typingActive: TypingActiveEmployeeChat = new TypingActiveEmployeeChat();
        typingActive.typing = check;
        typingActive.active = true;
        this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${this.contactIdLast}`).set(typingActive);
    }

    async changeActiveChat(check: boolean, id: string) {
        const typingActive: TypingActiveEmployeeChat = new TypingActiveEmployeeChat();
        typingActive.active = check;
        await this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${id}`).set(typingActive);
    }

    private getAllChatContents(): void {
        this.firebaseDB.database.ref(`chats/${this.token.getUsername()}`).once('value', resp => {
            const temp: any = this.snapshotToArray(resp);
            let checkLoadFirst: boolean = true;
            temp.forEach((e, i) => {
                this.firebaseDB.database.ref(`chats/${this.token.getUsername()}/${e.key}`).on('value', res => {
                    const chat: Chat = new Chat();
                    chat.id = e.key;
                    chat.dialog = this.snapshotToArray(res);
                    const indexDefaut: number = chat.dialog.findIndex(content => content.who === "");
                    chat.dialog.splice(indexDefaut, 1);
                    if (chat.dialog.length > 0) {
                        for (let ind = 0; ind < chat.dialog.length; ind++) {
                            if (chat.dialog[ind].status === "delete") {
                                chat.dialog.splice(ind, 1);
                                ind--;
                            }
                        }
                    }
                    // this.chat = chat;
                    const index = this.chats.findIndex(elm => elm.id === e.key);
                    if (index === -1) {
                        this.chats.push(chat);
                    } else {
                        this.chats[index] = chat;
                    }

                    if (this.selectedContact && this.selectedContact.id === e.key) {
                        this.chat = this.chats.find(element => element.id === e.key);
                        // Prepare the chat for the replies
                        this.prepareChatForReplies();

                    }
                    if (i === temp.length - 1) {
                        checkLoadFirst = false;
                    }
                    if (!checkLoadFirst) {
                        // sort time message
                        const tempArr: Chat[] = this.chats.sort((a, b) => {
                            if (a.dialog.length < 1) {
                                return 1;
                            }
                            if (b.dialog.length < 1) {
                                return -1;
                            }
                            return new Date(b.dialog[b.dialog.length - 1].time).getTime() - new Date(a.dialog[a.dialog.length - 1].time).getTime();


                        });
                        const result: UnreadEmployee[] = [];
                        tempArr.forEach(element => {
                            const val: UnreadEmployee = this.contacts.find(urd => urd.id === element.id);
                            result.push(val);
                        });
                        this.contacts = result;
                    }
                });
            })
        });
    }
    /**
     * Prepare the chat for the replies
     */
    private prepareChatForReplies(): void {
        setTimeout(() => {

            // Focus to the reply input
            // this._replyInput.nativeElement.focus();

            // Scroll to the bottom of the messages list
            if (this.chatViewScrollbar) {
                this.chatViewScrollbar.update();

                setTimeout(() => {
                    this.chatViewScrollbar.scrollToBottom(0);
                });
            }
        });
    }

    // -----------------------------------------------------------------------------------------------------
    // @ Public methods
    // -----------------------------------------------------------------------------------------------------

    /**
     * Fold the temporarily unfolded sidebar back
     */
    foldSidebarTemporarily(): void {
        this.fuseSidebarService.getSidebar('chatPanel').foldTemporarily();
    }

    /**
     * Unfold the sidebar temporarily
     */
    unfoldSidebarTemporarily(): void {
        this.fuseSidebarService.getSidebar('chatPanel').unfoldTemporarily();
    }

    /**
     * Toggle sidebar opened status
     */
    toggleSidebarOpen(): void {
        this.fuseSidebarService.getSidebar('chatPanel').toggleOpen();
    }

    /**
     * Decide whether to show or not the contact's avatar in the message row
     *
     * @param message
     * @param i
     * @returns {boolean}
     */
    shouldShowContactAvatar(message, i): boolean {
        return (
            message.who === this.selectedContact.id &&
            ((this.chat.dialog[i + 1] && this.chat.dialog[i + 1].who !== this.selectedContact.id) || !this.chat.dialog[i + 1])
        );
    }

    /**
     * Check if the given message is the first message of a group
     *
     * @param message
     * @param i
     * @returns {boolean}
     */
    isFirstMessageOfGroup(message, i): boolean {
        return (i === 0 || this.chat.dialog[i - 1] && this.chat.dialog[i - 1].who !== message.who);
    }

    /**
     * Check if the given message is the last message of a group
     *
     * @param message
     * @param i
     * @returns {boolean}
     */
    isLastMessageOfGroup(message, i): boolean {
        return (i === this.chat.dialog.length - 1 || this.chat.dialog[i + 1] && this.chat.dialog[i + 1].who !== message.who);
    }

    /**
     * Toggle chat with the contact
     *
     * @param contact
     */
    public toggleChat(contact): void {
        this.lastIndex = -1;
        this.contacts = this.tempContacts;
        // If the contact equals to the selectedContact,
        // that means we will deselect the contact and
        // unload the chat
        if (this.selectedContact && contact.id === this.selectedContact.id) {
            // Reset
            if (this.tempMess !== "") {
                const objTempMess: TempMessageEmployeeDTO = new TempMessageEmployeeDTO();
                objTempMess.id = this.contactIdLast;
                objTempMess.messageTemp = this.tempMess;
                const ind: number = this.tempMessages.findIndex(e => e.id === this.contactIdLast);
                if (ind === -1) {
                    this.tempMessages.push(objTempMess);
                } else {
                    this.tempMessages[ind] = objTempMess;
                }
            }
            this.resetChat();
            this.changeActiveChat(false, this.contactIdLast);
            this.contactIdLast = "";
        }
        // Otherwise, we will select the contact, open
        // the sidebar and start the chat
        else {
            this.checkShowSearch = false;
            if (this.tempMess !== "" && this.contactIdLast !== "") {
                const indexMess: number = this.tempMessages.findIndex(e => e.id === this.contactIdLast);
                const objTempMess: TempMessageEmployeeDTO = new TempMessageEmployeeDTO();
                objTempMess.id = this.contactIdLast;
                objTempMess.messageTemp = this.tempMess;
                if (indexMess === -1) {
                    this.tempMessages.push(objTempMess);
                } else {
                    this.tempMessages[indexMess] = objTempMess;
                }
            }
            const index: number = this.tempMessages.findIndex(e => e.id === contact.id);
            if (index !== -1) {
                this.tempMess = this.tempMessages[index].messageTemp;
            } else {
                this.tempMess = "";
            }
            // Unfold the sidebar temporarily
            this.unfoldSidebarTemporarily();
            // Set the selected contact
            this.selectedContact = contact;
            // Set active chat
            if (this.contactIdLast !== "") {
                this.changeActiveChat(false, this.contactIdLast);
            }
            this.contactIdLast = contact.id;
            this.changeActiveChat(true, contact.id);

            const ind = this.chats.findIndex(e => e.id === contact.id);
            this.chat = this.chats[ind];
            // Prepare the chat for the replies
            this.prepareChatForReplies();

        }
    }

    /**
     * Remove the selected contact and unload the chat
     */
    public resetChat(): void {
        // Set the selected contact as null
        this.selectedContact = null;
        this.txtSearch = "";
        // Set the chat as null
        this.chat = null;
    }

    /**
     * Reply
     */
    public reply(event): void {
        event.preventDefault();
        const ind: number = this.tempMessages.findIndex(e => e.id === this.chat.id);
        if (ind !== -1) {
            this.tempMessages[ind].messageTemp = "";
        }
        if (!this.replyForm.form.value.message) {
            return;
        }
        if(this.replyForm.form.value.message.replaceAll("\n","").length === 0){
            this.tempMess = this.replyForm.form.value.message.replaceAll("\n","");
            return;
        }
        // Message
        const message = {
            id: this.generateKey().trim().replace(/\./g, ''),
            who: this.user.id,
            message: this.replyForm.form.value.message,
            time: new Date().toISOString(),
            status: "active"
        };
        // Add the message to the chat
        this.chat.dialog.push(message);
        const index = this.chats.findIndex(elm => elm.id === this.chat.id);
        if (index === -1) {
            this.chats.push(this.chat);
        } else {
            this.chats[index] = this.chat;
        }
        // Reset the reply form
        this.replyForm.reset();
        // scroll when chat
        this.prepareChatForReplies();
        // Update the server

        this.firebaseDB.database.ref(`chats/${this.token.getUsername()}/${this.chat.id}/${message.id}`).set(message);
        this.firebaseDB.database.ref(`chats/${this.chat.id}/${this.token.getUsername()}/${message.id}`).set(message);
        // set unread
        this.firebaseDB.database.ref(`users/${this.chat.id}/status`).once('value', resp => {
            const status: string = resp.val();
            this.firebaseDB.database.ref(`counts/${this.chat.id}/${this.token.getUsername()}`).once('value', rs => {
                const active = rs.val().active;
                if (status === "offline" || !active) {
                    const typingActive: TypingActiveEmployeeChat = new TypingActiveEmployeeChat();
                    typingActive.unread = rs.val().unread + 1;
                    this.firebaseDB.database.ref(`counts/${this.chat.id}/${this.token.getUsername()}`).set(typingActive);
                }
            });
        });
        const typingActive: TypingActiveEmployeeChat = new TypingActiveEmployeeChat();
        typingActive.active = true;
        this.firebaseDB.database.ref(`counts/${this.token.getUsername()}/${this.chat.id}`).set(typingActive);
        this.setSeenChat(this.chat.id);
        // this.firebaseDB.database.ref('counts/users').set(users.length);
    }

    // 
    private snapshotToArray = (snapshot: any) => {
        const returnArr = [];

        snapshot.forEach((childSnapshot: any) => {
            const item = childSnapshot.val();
            item.key = childSnapshot.key;
            returnArr.push(item);
        });

        return returnArr;
    };

    private fading(start: boolean): void {
        const $element = $('#typingText');
        if (start) {
            $element.css("visibility", "visible");
            this.timerFade = setInterval(() => {
                $element.fadeIn(600, () => {
                    $element.fadeOut(900, () => {
                        $element.fadeIn(900)
                    });
                });
            }, 2500);
        } else {
            clearInterval(this.timerFade);
            $element.css("visibility", "hidden");
        }
    }

    public showTime(index: number): void {
        if (this.lastIndex === -1) {
            this.showNewTimeChat(index);
        } else if (this.lastIndex !== index) {
            this.showNewTimeChat(index);
        } else {
            this.clearTimeChat();
        }
    }

    private clearTimeChat(): void {
        const $time = $(`#time-${this.lastIndex}`);
        const $content = $(`#content-${this.lastIndex}`);
        $time.css("visibility", "hidden");
        if (this.chat.dialog[this.lastIndex].who === this.token.getUsername()) {
            $content.css("background-color", "#E0E0E0");
        } else {
            $content.css("background-color", "#2196f3");
        }
        this.lastIndex = -1;
    }

    private showNewTimeChat(index: number): void {
        const $time = $(`#time-${index}`);
        const $content = $(`#content-${index}`);
        $time.css("visibility", "visible");
        if (this.chat.dialog[index].who === this.token.getUsername()) {
            $content.css("background-color", "#ababab");
        } else {
            $content.css("background-color", "#0a65ab");
        }
        if (this.lastIndex !== -1) {
            this.clearTimeChat();
        }
        this.lastIndex = index;
    }

    public retrievalMessage(message: ChatContentDTO): void {
        if (this.selectedContact && message.who === this.token.getUsername()) {
            message.status = "retrieval"
            this.firebaseDB.database.ref(`chats/${this.token.getUsername()}/${this.selectedContact.id}/${message.id}`).set(message);
            this.firebaseDB.database.ref(`chats/${this.selectedContact.id}/${this.token.getUsername()}/${message.id}`).set(message);
        }
    }

    public deleteMessage(message: ChatContentDTO): void {
        if (this.selectedContact) {
            message.status = "delete";
            this.firebaseDB.database.ref(`chats/${this.token.getUsername()}/${this.selectedContact.id}/${message.id}`).set(message);
        }
    }

    private generateKey(): string {
        return new Date().toISOString() + uuidv4();
    }

    public clearText(): void {
        this.txtSearch = "";
        this.contacts = this.tempContacts;
    }

    public changeShowSearch(): void {
        this.checkShowSearch = !this.checkShowSearch;
        this.txtSearch = "";
        this.contacts = this.tempContacts;
    }

    public searchingContact(): void {
        this.contacts = this.tempContacts;
        const arrContacts: UnreadEmployee[] = [];
        this.contacts.forEach(e => {
            if (e.id.toLocaleLowerCase().includes(this.txtSearch.toLocaleLowerCase()) || e.name.toLocaleLowerCase().includes(this.txtSearch.toLocaleLowerCase())) {
                arrContacts.push(e);
            }
        });
        this.contacts = arrContacts;
    }

    // delete all message chat
    public deleteAll(id: string): void {
        Notiflix.Confirm.Init({ titleColor: "#ff0000", messageFontSize: "15px" });
        Notiflix.Confirm.Show(
            "Confirm Delete!",
            "Are you sure delete all chat with " + id + " ?",
            "Yes",
            "No",
            () => {
                const index: number = this.chats.findIndex(e => e.id === id);
                this.chats[index].dialog.forEach(el => {
                    el.status = "delete";
                    this.firebaseDB.database.ref(`chats/${this.token.getUsername()}/${this.selectedContact.id}/${el.id}`).set(el);

                });
            },
            () => {
                // No button callback
            },
        );

    }

    // back chat list contact
    public backChat(): void {
        this.resetChat();
    }
}
