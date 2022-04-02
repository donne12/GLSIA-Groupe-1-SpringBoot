import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CategoryService } from 'src/app/services/category/category.service';
import { Product, ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent implements OnInit {

  public category:any=[];
  public categoryObj: any=[] ;
  public product:Product=new Product;
  id:any;

  constructor(private router : Router,private categoryService:CategoryService,private productService:ProductService) {
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      id : any
    };
    this.id = state.id;
    
   }

  ngOnInit(): void {
    this.categoryService.getAllCategory().subscribe( response => {
      this.category = response;
     
  });
  this.productService.getProductById(this.id).subscribe(data=>{
     
        this.product=data;
       
  },error=>window.alert(error.error)
  );
  }

  getCategory(cid:number){

    this.categoryObj={"id":cid};
   
    
    }

  updateProduct(product:Product) {
    this.getCategory(product.category as any as number);
    product.mrp=this.product.mrp;
    product.stock=this.product.stock;
    product.tax=this.product.tax;
    product.category=this.categoryObj;
   
    this.productService.updateProduct(this.id,product).subscribe((response) => {
      window.alert(response);
      this.router.navigate(['/manageProduct']);
    })
  }


}
