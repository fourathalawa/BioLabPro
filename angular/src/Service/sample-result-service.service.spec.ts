import { TestBed } from '@angular/core/testing';

import { SampleResultServiceService } from './sample-result-service.service';

describe('SampleResultServiceService', () => {
  let service: SampleResultServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SampleResultServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
