import { TestBed } from '@angular/core/testing';

import { ProductstaffService } from './productstaff.service';

describe('ProductstaffService', () => {
  let service: ProductstaffService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductstaffService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
