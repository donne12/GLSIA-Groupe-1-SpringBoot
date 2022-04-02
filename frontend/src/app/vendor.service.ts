import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class VendorService {
  private baseUrl = 'http://localhost:8083/api/vendor';

  constructor(private http: HttpClient) {}

  getVendor(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createVendor(vendor: Object): Observable<Object> {
    const headers = new HttpHeaders().set(
      'Content_Type',
      'text/plain ;charset=utf-8'
    );
    return this.http.post(`${this.baseUrl}`, vendor, {
      headers,
      responseType: 'text',
    });
  }

  updateVendor(id: number, value: any): Observable<Object> {
    const headers = new HttpHeaders().set(
      'Content_Type',
      'text/plain ;charset=utf-8'
    );
    return this.http.put(`${this.baseUrl}/${id}`, value, {
      headers,
      responseType: 'text',
    });
  }

  deleteVendor(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getVendorsList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
