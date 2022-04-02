import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category/category.service';
import { Product, ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  public category:any=[];
  public categoryObj: any=[];

  constructor(private categoryService:CategoryService,private productService:ProductService,private router:Router) {
   }

  ngOnInit(): void {
    this.categoryService.getAllCategory().subscribe( response => {
      this.category = response;
  });
  }

  getCategory(cid:number){

    this.categoryObj={"id":cid};
    console.log(this.categoryObj);
    
    }

  addProduct(product:Product) {
    this.getCategory(product.category as any as number);
    product.category=this.categoryObj;
    console.log(product);
    this.productService.createProduct(product).subscribe((response) => {
      window.alert(response);
      this.router.navigate(['/viewProduct']);
    })
  }
}
