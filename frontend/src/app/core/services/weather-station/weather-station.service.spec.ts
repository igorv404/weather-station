import { TestBed } from '@angular/core/testing';

import { WeatherStationService } from './weather-station.service';

describe('WeatherStationService', () => {
  let service: WeatherStationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WeatherStationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
