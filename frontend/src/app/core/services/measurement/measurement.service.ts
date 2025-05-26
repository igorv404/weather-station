import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Measurements} from '../../../shared/models/responses/measurement.response';
import {MeasurementResponseTypes} from '../../../shared/models/requests/measurement.request';

@Injectable({
  providedIn: 'root'
})
export class MeasurementService {
  constructor(private http: HttpClient) {}

  public getMeasurements(type: MeasurementResponseTypes, weatherStation: number, from?: Date, to?: Date) {
    let params = new HttpParams()
      .set('type', type.toString())
      .set('weatherStation', weatherStation);

    if (from) params = params.set('from', from.toISOString().split('T')[0]);

    if (to) params = params.set('to', to.toISOString().split('T')[0]);

    return this.http.get<Measurements>('http://localhost:8080/measurements', {
      params: params
    });
  }
}
