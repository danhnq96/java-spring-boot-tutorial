import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceListSearchComponent } from './invoice-list-search.component';

describe('InvoiceListSearchComponent', () => {
  let component: InvoiceListSearchComponent;
  let fixture: ComponentFixture<InvoiceListSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceListSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceListSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
