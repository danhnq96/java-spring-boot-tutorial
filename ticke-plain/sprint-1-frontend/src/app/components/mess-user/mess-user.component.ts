import {Component, ElementRef, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import * as firebase from 'firebase';
import {DatePipe} from '@angular/common';
import {EmojiEvent} from "@ctrl/ngx-emoji-mart/ngx-emoji";

const CUSTOM_EMOJIS = [];

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
  selector: 'app-mess-user',
  templateUrl: './mess-user.component.html',
  styleUrls: ['./mess-user.component.css']
})
export class MessUserComponent implements OnInit {

  public listMess = [];
  public listIcon = [];
  public room = '';
  formSend: FormGroup;
  formSendRequest: FormGroup;
  firstRequest: FormGroup;
  checkRequest: boolean;
  CUSTOM_EMOJIS = CUSTOM_EMOJIS;
  set = 'native';
  native = true;

  refUser = firebase.database().ref('users/');

  constructor(private el: ElementRef, private fb: FormBuilder, private datePipe: DatePipe) {
    firebase.database().ref('chats/').on('value', resp => {
      this.listMess = [];
      this.listMess = snapshotToArray(resp, this.room);
      $('.chat-body').scrollTop($('.chat-body')[0].scrollHeight);
    });
    setInterval(() => {
      this.checkRequest = this.firstRequest.value.name !== "" && this.firstRequest.value.email !== "" && this.firstRequest.value.phone !== "";
    }, 1000);
  }

  ngOnInit() {
    this.formSendRequest = this.fb.group({
      name: ['', [Validators.required, Validators.maxLength(25)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern('^\\d{10,12}$')]]
    });
    this.firstRequest = this.formSendRequest;
    this.formSend = this.fb.group({
      content: ['', Validators.required],
      isUser: ''
    });
    $(document).ready(() => {
      $('.openChatBtn').click(() => {
        if (this.checkRequest) {
          $('.requiredChat').hide();
          $('.openChat').show();
        } else {
          $('.requiredChat').show();
          $('.openChatBtn').hide();
        }
      });
      $('.close').click(() => {
        $('.openChat').hide();
        $('.requiredChat').hide();
        $('.openChatBtn').show();
      }),
        $('.begin-chat').click(() => {
          if (this.formSendRequest.valid) {
            $('.openChat').hide();
            $('.requiredChat').hide();
            $('.openChat').show();
            $('#icon-box').hide();
          }
        }),
        $('#icon').click(() => {
            $('#icon-box').toggle();
            setInterval(() => {
              $('.emoji-mart-anchor-selected').hide();
            }, 100);
          }
        );
      $('#icon-upload-file').click(() => {
        $('#file-upload')[0].click();
      });
      $('#btn-submit-mess').click(() => {
        $('#content-mess').val('');
      });
    });
  }

  sendMessage() {
    this.formSend.markAllAsTouched();
    for (const key of Object.keys(this.formSend.controls)) {
      if (this.formSend.controls[key].invalid) {
        const invalidControl = this.el.nativeElement.querySelector('[formControlName="' + key + '"]');
        invalidControl.focus();
        break;
      }
    }
    if (this.formSend.valid) {
      const chat = this.formSend.value;
      chat.nickName = this.formSendRequest.value.name;
      chat.roomId = this.room;
      chat.isUser = 'true';
      chat.sendDate = this.datePipe.transform(new Date(), 'dd/MM/yyyy HH:mm:ss');
      chat.type = 'message';
      const newMessage = firebase.database().ref('chats/').push();
      newMessage.set(chat);
      this.formSend = this.fb.group({
        content: ['', Validators.required],
        isUser: ''
      });
      firebase.database().ref('chats/').on('value', resp => {
        this.listMess = [];
        this.listMess = snapshotToArray(resp, this.room);
      });
      firebase.database().ref('users/').orderByChild('roomId').equalTo(this.room).once('value', (snapShot) => {
        if (!snapShot.exists()) {
          const newUser = firebase.database().ref('users/').push();
          newUser.set(this.firstRequest.value);
          localStorage.setItem('roomId', this.firstRequest.value.roomId);
        }
      });
    }
  }

  sendRequest() {
    for (const key of Object.keys(this.formSendRequest.controls)) {
      if (this.formSendRequest.controls[key].invalid) {
        const invalidControl = this.el.nativeElement.querySelector('[formControlName="' + key + '"]');
        invalidControl.focus();
        break;
      }
    }
    this.formSendRequest.markAllAsTouched();
    if (this.formSendRequest.valid) {
      const request = this.formSendRequest.value;
      request.roomId = request.phone + Math.round(Math.random() * 10000);
      this.room = request.roomId;
      this.refUser.orderByChild('roomId').equalTo(request.roomId).once('value', (snapshot) => {
        if (snapshot.exists()) {
          localStorage.setItem('roomId', request.roomId);
        } else {
          const newUser = firebase.database().ref('users/').push();
          newUser.set(request);
          localStorage.setItem('roomId', request.roomId);
        }
      });
      const firstMess = firebase.database().ref('chats/').push();
      firstMess.set({
        content: 'Xin chào bạn ' + request.name + ', chúng tôi sẽ phản hồi cho bạn trong vòng vài giờ',
        isUser: 'false',
        nickName: 'admin',
        roomId: request.roomId, sendDate: this.datePipe.transform(new Date(), 'dd/MM/yyyy HH:mm:ss'), type: 'message'
      });
      this.firstRequest = this.formSendRequest;
      this.formSendRequest = this.fb.group({
        name: ['', [Validators.required, Validators.maxLength(25)]],
        email: ['', [Validators.required, Validators.email]],
        phone: ['', [Validators.required, Validators.pattern('^\\d{10,12}$')]]
      });
    }
  }

  handleClick($event: EmojiEvent) {
    // console.log($event.emoji);
  }
}


