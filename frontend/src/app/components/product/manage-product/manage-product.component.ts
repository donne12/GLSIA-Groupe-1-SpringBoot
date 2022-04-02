import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css']
})
export class ManageProductComponent implements OnInit {

  public viewProduct?:any=[];

  constructor(private router:Router,private productService:ProductService) { }

   ngOnInit(): any {
    this.productService.getAllProduct().subscribe( response => {
      this.viewProduct = response;
  });
  }

  deleteProduct(id:number,categoryId:number) {
    this.productService.deleteProduct(id,categoryId).subscribe(response => {
          console.log(response);
          window.alert(response);
          this.router.navigate(['/viewProduct']);
  });
  }

  updateProduct(id:number) {
    this.router.navigate(["updateProduct"],{state:{id:id}});
  }

}
