import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckinOnlineComponent } from './checkin-online.component';

describe('CheckinOnlineComponent', () => {
  let component: CheckinOnlineComponent;
  let fixture: ComponentFixture<CheckinOnlineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckinOnlineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckinOnlineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
