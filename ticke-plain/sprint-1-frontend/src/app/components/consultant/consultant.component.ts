import {Component, OnInit} from '@angular/core';
import * as firebase from 'firebase';
import {Router} from '@angular/router';

export const snapshotToArray = (snapshot: any) => {
  const returnArr = [];

  snapshot.forEach((childSnapshot: any) => {
    const item = childSnapshot.val();
    item.key = childSnapshot.key;
    returnArr.push(item);
  });
  return returnArr;
};

@Component({
  selector: 'app-consultant',
  templateUrl: './consultant.component.html',
  styleUrls: ['./consultant.component.css']
})
export class ConsultantComponent implements OnInit {

  listUser = [];

  constructor(private router: Router) {
    firebase.database().ref('users/').on('value', (resp) => {
      this.listUser = snapshotToArray(resp).reverse();
      if (this.listUser.length === 0) {
        this.router.navigate(['/consultant']);
      }
      this.router.navigate(['/consultant/mess/' + this.listUser[0].roomId]);
    });
  }

  ngOnInit() {

    setInterval(() => {
      firebase.database().ref('chats/').remove().then(() => {
      });
      firebase.database().ref('users/').remove().then(() => {
      });
    }, 12000000);
  }


}
