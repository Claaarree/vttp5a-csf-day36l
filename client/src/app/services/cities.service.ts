import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { City } from '../model/city';

@Injectable({
  providedIn: 'root'
})
export class CitiesService {
  private httpClient = inject(HttpClient);

  getCities() {
    return lastValueFrom(this.httpClient.get<City[]>('/api/cities'));
  }

}
