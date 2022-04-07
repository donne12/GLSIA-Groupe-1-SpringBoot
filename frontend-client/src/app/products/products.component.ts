import { Component, OnInit } from '@angular/core';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css'],
})
export class ProductsComponent implements OnInit {
  products = [];
  constructor(private productService: ProductService) {}

  ngOnInit() {
    console.log('hello');
    this.productService.getProducts().subscribe((res) => {
      console.log(res);
      if (res != null){
        this.products = res;
      }
    
    });
  }
}
