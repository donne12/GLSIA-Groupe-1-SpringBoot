import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StateService {

  constructor(private http : HttpClient) { }

  create(state : State) {
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    const newCountry = {
      name : state.name
    }
    return this.http.post("http://localhost:8083/api/state/country/"+state.country+"/save", newCountry,{headers, responseType : 'text'} );
  }
  
  get(id:any) : Observable<any> {
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.get<State>("http://localhost:8083/api/state/"+id);
  }
}
export class State {
  id?:number;
  name?:string;
  country?:any;
}
