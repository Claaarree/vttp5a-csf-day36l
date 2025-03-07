import { Component, inject, OnInit } from '@angular/core';
import { CitiesService } from '../../services/cities.service';
import { CityStore } from '../../store/city.store';
import { City } from '../../model/city';

@Component({
  selector: 'app-cities',
  standalone: false,
  templateUrl: './cities.component.html',
  styleUrl: './cities.component.css'
})
export class CitiesComponent implements OnInit{

  private citiesSvc = inject(CitiesService)
  private citiesStore = inject(CityStore);
  cities!: City[]

  async ngOnInit() {
    this.cities = await this.citiesSvc.getCities();
    this.cities.forEach((cityObj) => {
      console.log(cityObj.code);
      console.log(cityObj.city_name);
      this.citiesStore.addNewCity(cityObj)
    })
  }

}
