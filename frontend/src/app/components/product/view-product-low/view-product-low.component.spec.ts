import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewProductComponentLow } from './view-product-low.component';

describe('ViewProductComponent', () => {
  let component: ViewProductComponentLow;
  let fixture: ComponentFixture<ViewProductComponentLow>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewProductComponentLow ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewProductComponentLow);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
