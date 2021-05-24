import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangePasswordSuccessfullyComponent } from './change-password-successfully.component';

describe('ChangePasswordSuccessfullyComponent', () => {
  let component: ChangePasswordSuccessfullyComponent;
  let fixture: ComponentFixture<ChangePasswordSuccessfullyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangePasswordSuccessfullyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangePasswordSuccessfullyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
