import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css'],
})
export class ProductComponent implements OnInit {
  product = null;
  constructor(
    private activatedRoute: ActivatedRoute,
    private productService: ProductService
  ) {}
  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((param) => {
      this.productService.getProduct(param.get('id')).subscribe((res) => {
        this.product = res;
      });
    });
  }
}
