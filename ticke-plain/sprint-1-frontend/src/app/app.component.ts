import {Component} from '@angular/core';
import * as firebase from 'firebase';

const firebaseConfig = {
  apiKey: 'AIzaSyAAdvYypK_iowvvjWxLvBpJUDyBTlO8v-Q',
  authDomain: 'codegym-sprint1.firebaseapp.com',
  databaseURL: 'https://codegym-sprint1-default-rtdb.firebaseio.com',
  projectId: 'codegym-sprint1',
  storageBucket: 'codegym-sprint1.appspot.com',
  messagingSenderId: '878349772718',
  appId: '1:878349772718:web:8e1a0d84e780af38694a3f',
  measurementId: 'G-BV9J90XWD3'
};

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'pj-sprint1';

  constructor() {
    firebase.initializeApp(firebaseConfig);
  }
}
