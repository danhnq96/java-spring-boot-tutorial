import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SuccessfullyPaidPendingTicketComponent } from './successfully-paid-pending-ticket.component';

describe('SuccessfullyPaidPendingTicketComponent', () => {
  let component: SuccessfullyPaidPendingTicketComponent;
  let fixture: ComponentFixture<SuccessfullyPaidPendingTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SuccessfullyPaidPendingTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SuccessfullyPaidPendingTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
