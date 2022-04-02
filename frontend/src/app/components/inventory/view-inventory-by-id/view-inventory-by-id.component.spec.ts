import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByIdComponent } from './view-inventory-by-id.component';

describe('ViewInventoryByIdComponent', () => {
  let component: ViewInventoryByIdComponent;
  let fixture: ComponentFixture<ViewInventoryByIdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByIdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByIdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
