import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:8083/api/customer';

  constructor(private route:Router,private http:HttpClient) { }

  createCustomer(customer:Customer){
    return this.http.post(`${this.baseUrl}`, customer,{responseType: 'text'});
  }

  getAllCustomer():Observable<any>{
    return this.http.get<Customer>(`${this.baseUrl}`);
  }

  getCustomerById(id:any):Observable<any>{
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.get<Customer>(`${this.baseUrl}/${id}`);
  }

  

}
export class Customer{
  phoneNumber?:number;
  name?:string;
  email?:string;
  password?:string;

}