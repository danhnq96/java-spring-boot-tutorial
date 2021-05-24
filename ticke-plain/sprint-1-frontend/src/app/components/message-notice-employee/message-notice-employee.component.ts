import {Component, Inject, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-message-notice-employee',
  templateUrl: './message-notice-employee.component.html',
  styleUrls: ['./message-notice-employee.component.css']
})
export class MessageNoticeEmployeeComponent implements OnInit {
  private message = 'Nothing';
  private path = '/list-employee';

  constructor(
    private activedRouter: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activedRouter.params.subscribe(data => {
      this.message = data.message;
      if (data.path !== undefined) {
        this.path = data.path;
      }
    });
  }
}
