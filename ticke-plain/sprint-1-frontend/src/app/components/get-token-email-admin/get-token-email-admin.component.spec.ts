import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetTokenEmailAdminComponent } from './get-token-email-admin.component';

describe('GetTokenEmailAdminComponent', () => {
  let component: GetTokenEmailAdminComponent;
  let fixture: ComponentFixture<GetTokenEmailAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetTokenEmailAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetTokenEmailAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
