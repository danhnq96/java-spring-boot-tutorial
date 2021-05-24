import { TestBed } from '@angular/core/testing';

import { LuggageService } from './luggage.service';

describe('LuggageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LuggageService = TestBed.get(LuggageService);
    expect(service).toBeTruthy();
  });
});
