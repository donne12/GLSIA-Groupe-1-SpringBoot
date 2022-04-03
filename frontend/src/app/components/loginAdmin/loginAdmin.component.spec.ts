import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponentAdmin } from './loginAdmin.component';

describe('LoginComponent', () => {
  let component: LoginComponentAdmin;
  let fixture: ComponentFixture<LoginComponentAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponentAdmin ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponentAdmin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
