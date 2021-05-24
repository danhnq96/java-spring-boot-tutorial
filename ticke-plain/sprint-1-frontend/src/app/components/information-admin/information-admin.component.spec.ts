import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InformationAdminComponent } from './information-admin.component';

describe('InformationAdminComponent', () => {
  let component: InformationAdminComponent;
  let fixture: ComponentFixture<InformationAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InformationAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InformationAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
