import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../staff/customer/customer.service';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http : HttpClient) { }

  create(address:Address, id:number) :Observable<any>{
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    const newaddr = {
      addressLine : address.addressLine,
      city : address.city,
      pinCode : address.pinCode
    }
    return this.http.post("http://localhost:8083/api/add-address/"+address.state+"/"+id, newaddr, {headers, responseType: 'text'} );
  }

  get(id:any) :Observable<any>{
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.get<Address>("http://localhost:8083/api/get-address/"+id);
  }
}

export class Address{
  public id?:number;
  public addressLine?:string;
  public city?:string;
  public pinCode?:number;
  public state?:any;
  public customer?:Customer
}