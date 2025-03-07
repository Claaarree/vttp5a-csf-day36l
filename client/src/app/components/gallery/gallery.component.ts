import { Component, inject, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { City } from '../../model/city';
import { CityStore } from '../../store/city.store';

@Component({
  selector: 'app-gallery',
  standalone: false,
  templateUrl: './gallery.component.html',
  styleUrl: './gallery.component.css'
})
export class GalleryComponent implements OnInit{
  ngOnInit(): void {
    this.loadCities();
  }

  private cityStore = inject(CityStore)
  citiesList$!: Observable<City[]>;
  selectedCity: String = '';

  loadCities() {
    this.citiesList$ = this.cityStore.cities$;
    this.cityStore.loadCities();
  }

}
