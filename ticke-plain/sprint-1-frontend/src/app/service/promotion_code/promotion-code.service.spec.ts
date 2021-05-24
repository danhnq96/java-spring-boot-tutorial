import { TestBed } from '@angular/core/testing';

import { PromotionCodeService } from './promotion-code.service';

describe('PromotionCodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PromotionCodeService = TestBed.get(PromotionCodeService);
    expect(service).toBeTruthy();
  });
});
