import { TestBed, inject } from '@angular/core/testing';

import { TasaRepresentativaService } from './tasa-representativa.service';

describe('TasaRepresentativaService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TasaRepresentativaService]
    });
  });

  it('should be created', inject([TasaRepresentativaService], (service: TasaRepresentativaService) => {
    expect(service).toBeTruthy();
  }));
});
