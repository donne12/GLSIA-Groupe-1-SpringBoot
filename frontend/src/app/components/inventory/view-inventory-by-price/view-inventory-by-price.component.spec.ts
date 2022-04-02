import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByPriceComponent } from './view-inventory-by-price.component';

describe('ViewInventoryByPriceComponent', () => {
  let component: ViewInventoryByPriceComponent;
  let fixture: ComponentFixture<ViewInventoryByPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByPriceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
