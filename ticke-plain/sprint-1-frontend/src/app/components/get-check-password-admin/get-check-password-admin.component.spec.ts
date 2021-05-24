import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCheckPasswordAdminComponent } from './get-check-password-admin.component';

describe('GetCheckPasswordAdminComponent', () => {
  let component: GetCheckPasswordAdminComponent;
  let fixture: ComponentFixture<GetCheckPasswordAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCheckPasswordAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCheckPasswordAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
