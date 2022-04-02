import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByProductNameComponent } from './view-inventory-by-product-name.component';

describe('ViewInventoryByProductNameComponent', () => {
  let component: ViewInventoryByProductNameComponent;
  let fixture: ComponentFixture<ViewInventoryByProductNameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByProductNameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByProductNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
