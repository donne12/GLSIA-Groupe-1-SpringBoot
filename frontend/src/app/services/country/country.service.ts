import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CountryService {

  constructor(private http : HttpClient) { }

  create(country : Country) {
  
    return this.http.post("http://localhost:8083/api/country/save", country, {responseType: 'text'} );
  }
  
  get() : Observable<any> {
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.get<Country>("http://localhost:8083/api/countries");
  }
}
export class Country {
  id?:number;
  name?:string;
}