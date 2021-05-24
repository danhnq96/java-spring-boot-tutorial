import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoPassengerBookingTicketComponent } from './info-passenger-booking-ticket.component';

describe('InfoPassengerBookingTicketComponent', () => {
  let component: InfoPassengerBookingTicketComponent;
  let fixture: ComponentFixture<InfoPassengerBookingTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InfoPassengerBookingTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InfoPassengerBookingTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
