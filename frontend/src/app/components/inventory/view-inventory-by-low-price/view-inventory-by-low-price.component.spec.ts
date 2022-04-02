import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByLowPriceComponent } from './view-inventory-by-low-price.component';

describe('ViewInventoryByLowPriceComponent', () => {
  let component: ViewInventoryByLowPriceComponent;
  let fixture: ComponentFixture<ViewInventoryByLowPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByLowPriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByLowPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
