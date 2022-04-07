import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private baseUrl = 'http://localhost:8083/api/';
  constructor(private httpClient: HttpClient) {}

  public getOrders(customerId): Observable<any> {
    return this.httpClient.get(this.baseUrl + 'get-orders/' + '4000');
  }

  public getOrder(orderId): Observable<any> {
    return this.httpClient.get(this.baseUrl + 'get-order/' + orderId);
  }
}
