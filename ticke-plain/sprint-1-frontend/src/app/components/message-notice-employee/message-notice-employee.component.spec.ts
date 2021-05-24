import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessageNoticeEmployeeComponent } from './message-notice-employee.component';

describe('MessageNoticeEmployeeComponent', () => {
  let component: MessageNoticeEmployeeComponent;
  let fixture: ComponentFixture<MessageNoticeEmployeeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MessageNoticeEmployeeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessageNoticeEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
