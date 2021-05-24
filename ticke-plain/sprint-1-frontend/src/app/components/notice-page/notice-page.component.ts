import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-notice-page',
  templateUrl: './notice-page.component.html',
  styleUrls: ['./notice-page.component.css']
})
export class NoticePageComponent implements OnInit {
  protected message = 'Nothing';
  protected path = 'Empty';

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
