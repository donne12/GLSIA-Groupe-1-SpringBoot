import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByHighPriceComponent } from './view-inventory-by-high-price.component';

describe('ViewInventoryByHighPriceComponent', () => {
  let component: ViewInventoryByHighPriceComponent;
  let fixture: ComponentFixture<ViewInventoryByHighPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByHighPriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByHighPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
