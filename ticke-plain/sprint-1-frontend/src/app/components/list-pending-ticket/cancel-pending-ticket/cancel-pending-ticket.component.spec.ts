import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CancelPendingTicketComponent } from './cancel-pending-ticket.component';

describe('CancelPendingTicketComponent', () => {
  let component: CancelPendingTicketComponent;
  let fixture: ComponentFixture<CancelPendingTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CancelPendingTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CancelPendingTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
