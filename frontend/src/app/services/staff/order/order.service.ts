import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from './order';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private baseUrl='http://localhost:8083/api'
  constructor(private http:HttpClient) { }
  addOrder(customerId:number,addressId:number,order:object):Observable<any>
  {
    return this.http.post(`${this.baseUrl}`+'/add-order/'+`${customerId}`+'/'+`${addressId}`,order,{responseType:'text'})
  }
  addOrderItem(orderId:number,productId:number,orderItem:object):Observable<any>
  {
    return this.http.post(`${this.baseUrl}`+'/order-item/'+'/order/'+`${orderId}`+'/product/'+`${productId}`+'/add-item',orderItem,{responseType:'text'})
  }
  getOrderId(customerId:number):Observable<any>
  {
    return this.http.get(`${this.baseUrl}`+'/order/'+`${customerId}`)
  }
  getOrderedItems(orderId:number):Observable<any>
  {
    return this.http.get(`${this.baseUrl}`+'/order-item/get-items/'+`${orderId}`)
  }
  getOrderById(orderId:number):Observable<any> 
  {
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.get<Order>(`${this.baseUrl}`+'/get-order/'+`${orderId}`)
  }
  updateOrder(orderId:number,order:object):Observable<any>
  {
    return this.http.put(`${this.baseUrl}`+'/update-order/'+`${orderId}`,order,{responseType:'text'})
  }
  generateBillReceipt(orderId:number) : Observable<any> {
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.get("http://localhost:8083/api/get-bill/pdf/?p="+`${orderId}`);
  }
  
}
