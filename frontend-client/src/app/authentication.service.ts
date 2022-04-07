import { HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {
  private baseUrl = 'https://localhost:8086/customer/';
  httpOptions = {
    headers: new HttpHeaders({
      Accept: 'application/json',
      'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient) {}
  public login(credentials): Observable<any> {
    return this.httpClient.post(
      this.baseUrl + 'authenticate',
      credentials,
      this.httpOptions
    );
  }
}
