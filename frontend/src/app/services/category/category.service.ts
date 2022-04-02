import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private baseUrl = 'http://localhost:8083/api/category';

  constructor(private http: HttpClient) { }

  createCategory(category : Category) : Observable<any>{
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.post(`${this.baseUrl}`, category,{ headers,responseType: 'text'});
  }


  getAllCategory() : Observable<any>{
    return this.http.get<Category>(`${this.baseUrl}`);
  }

  getCategoryById(id:number) : Observable<any>{
    return this.http.get<Category>(`${this.baseUrl}/${id}`);
  }

  deleteCategory(id:number): Observable<any>{
    console.log(id);
    return this.http.delete(`${this.baseUrl}/${id}`,{ responseType: 'text'});
  }

  updateCategory(id:number,category:Category){
   
    const headers =new HttpHeaders().set('Content_Type', 'text/plain ;charset=utf-8');
    return this.http.put(`${this.baseUrl}/${id}`, category, { headers, responseType: 'text'});
  }
}
export class Category{
  id?:number;
  name?:string;

}