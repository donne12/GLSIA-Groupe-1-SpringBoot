import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewInventoryByDateComponent } from './view-inventory-by-date.component';

describe('ViewInventoryByDateComponent', () => {
  let component: ViewInventoryByDateComponent;
  let fixture: ComponentFixture<ViewInventoryByDateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewInventoryByDateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewInventoryByDateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
