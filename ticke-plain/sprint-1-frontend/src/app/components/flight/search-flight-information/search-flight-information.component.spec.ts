import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {SearchFlightInformationComponent} from './search-flight-information.component';

describe('SearchFlightInformationComponent', () => {
  let component: SearchFlightInformationComponent;
  let fixture: ComponentFixture<SearchFlightInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [SearchFlightInformationComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchFlightInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
