import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartsComponentAdmin } from './charts.component';

describe('chartsComponent', () => {
  let component: ChartsComponentAdmin;
  let fixture: ComponentFixture<ChartsComponentAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartsComponentAdmin ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartsComponentAdmin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
