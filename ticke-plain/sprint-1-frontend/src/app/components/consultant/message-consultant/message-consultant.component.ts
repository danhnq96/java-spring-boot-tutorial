import {Component, ElementRef, OnInit} from '@angular/core';
import {Subscription} from 'rxjs';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import * as firebase from 'firebase';
import {DatePipe} from '@angular/common';
import {EmojiEvent} from "@ctrl/ngx-emoji-mart/ngx-emoji";


declare var $: any;

export const snapshotToArray = (snapshot: any, room: any) => {
  const returnArr = [];

  snapshot.forEach((childSnapshot: any) => {
    const item = childSnapshot.val();
    if (item.roomId === room) {
      item.key = childSnapshot.key;
      returnArr.push(item);
    }
  });
  return returnArr;
};

@Component({
  selector: 'app-message-consultant',
  templateUrl: './message-consultant.component.html',
  styleUrls: ['./message-consultant.component.css']
})
export class MessageConsultantComponent implements OnInit {

  room: string;
  formSendMess: FormGroup;
  listMessage = [];
  set = 'native';
  native = true;

  sub: Subscription;

  constructor(private activatedRoute: ActivatedRoute,
              private fb: FormBuilder,
              private datePipe: DatePipe,
              private el: ElementRef) {
  }

  ngOnInit() {
    this.sub = this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.room = paramMap.get('id');
      firebase.database().ref('chats/').on('value', (resp) => {
        this.listMessage = snapshotToArray(resp, this.room);
      });
    });
    this.formSendMess = this.fb.group({
      content: ['', Validators.required],
      isUser: ''

    });
    $(document).ready(() => {
      $('#icon-box-consultant').hide();
      $('#submit-consultant').click(() => {
        $('#content-chat-cons').val('');
      });
      $('#consultant-icon').click(() => {
          $('#icon-box-consultant').toggle(500);
        }
      );
    });
  }

  sendMess() {
    this.formSendMess.markAllAsTouched();
    for (const key of Object.keys(this.formSendMess.controls)) {
      if (this.formSendMess.controls[key].invalid) {
        const invalidControl = this.el.nativeElement.querySelector('[formControlName="' + key + '"]');
        invalidControl.focus();
        break;
      }
    }
    if (this.formSendMess.valid) {
      const chat = this.formSendMess.value;
      chat.nickName = 'admin';
      chat.roomId = this.room;
      chat.isUser = 'false';
      chat.sendDate = this.datePipe.transform(new Date(), 'dd/MM/yyyy HH:mm:ss');
      chat.type = 'message';
      const newMessage = firebase.database().ref('chats/').push();
      newMessage.set(chat);
      this.formSendMess = this.fb.group({
        content: ['', Validators.required],
        isUser: '',
      });
    }
  }

  handleClick($event: EmojiEvent) {
    // console.log($event.emoji);
  }
}
