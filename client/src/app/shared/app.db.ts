import Dexie, {Table} from 'dexie';
import { City } from '../model/city';

export class AppDB extends Dexie{
    // is a table and a list of cities
    cities!: Table<City, string>;

    constructor() {
        super('fileUpload');
        this.version(1).stores({
            cities: 'code, city_name'
        });
    }

    async addCity(item: City) {
        await this.cities.put(item)
    }
}

export const db = new AppDB();