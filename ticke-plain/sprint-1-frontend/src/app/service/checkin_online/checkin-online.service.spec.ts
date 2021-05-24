import { TestBed } from '@angular/core/testing';

import { CheckinOnlineService } from './checkin-online.service';

describe('CheckinOnlineService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CheckinOnlineService = TestBed.get(CheckinOnlineService);
    expect(service).toBeTruthy();
  });
});
