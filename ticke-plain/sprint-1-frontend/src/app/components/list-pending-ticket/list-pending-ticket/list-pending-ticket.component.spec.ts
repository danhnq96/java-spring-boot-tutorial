import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPendingTicketComponent } from './list-pending-ticket.component';

describe('ListPendingTicketComponent', () => {
  let component: ListPendingTicketComponent;
  let fixture: ComponentFixture<ListPendingTicketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListPendingTicketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPendingTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
