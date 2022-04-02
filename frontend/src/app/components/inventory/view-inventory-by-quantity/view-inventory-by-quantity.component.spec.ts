import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByQuantityComponent } from './view-inventory-by-quantity.component';

describe('ViewInventoryByQuantityComponent', () => {
  let component: ViewInventoryByQuantityComponent;
  let fixture: ComponentFixture<ViewInventoryByQuantityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByQuantityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByQuantityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
