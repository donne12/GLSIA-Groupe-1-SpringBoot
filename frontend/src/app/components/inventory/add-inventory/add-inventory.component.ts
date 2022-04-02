import { Component, OnInit } from '@angular/core';
import { Product, ProductService } from 'src/app/services/product/product.service';
import { VendorService } from 'src/app/vendor.service';
import { Vendor } from 'src/app/vendor';
import { Inventory, InventoryService } from 'src/app/services/inventory/inventory.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-inventory',
  templateUrl: './add-inventory.component.html',
  styleUrls: ['./add-inventory.component.css']
})
export class AddInventoryComponent implements OnInit {

  public product : any=[];
  public vendor : any=[]

  constructor(private productService : ProductService, private vendorService : VendorService, private inventoryService : InventoryService,private router:Router) { }

  ngOnInit(): void {
    this.productService.getAllProduct().subscribe((response) => {
      this.product = response;
    });

    this.vendorService.getVendorsList().subscribe((response) => {
      this.vendor = response;
    })
  }

  addInventory(productRequest : Inventory) {
    productRequest.purchasedPrice = Number(productRequest.purchasedPrice);
    productRequest.tax = Number(productRequest.tax);
    console.log(productRequest);
    this.inventoryService.createInventory(productRequest).subscribe((res)=>{
      window.alert(res);
      this.router.navigate(['/viewinventory']);
    });
  }
}
