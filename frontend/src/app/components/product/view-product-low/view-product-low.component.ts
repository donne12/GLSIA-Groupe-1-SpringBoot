import { Component, OnInit } from '@angular/core';
import {
  Inventory,
  InventoryService,
} from 'src/app/services/inventory/inventory.service';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-view-product',
  templateUrl: './view-product-low.component.html',
  styleUrls: ['./view-product-low.component.css'],
})
export class ViewProductComponentLow implements OnInit {
  public viewProduct?: any = [];
  public viewInventory?: any = [];
  public viewProductExpiredDates?: any = [];
  setProdctExpiredDate() {
    for (let p of this.viewInventory) {
      this.viewProductExpiredDates.push(p.product.name);
    }
  }

  public today: Date = new Date();
  constructor(
    private productService: ProductService,
    private inventoryService: InventoryService
  ) {}

  ngOnInit(): any {
    this.productService.getAllProduct().subscribe((response) => {
      this.viewProduct = response;
    });
    this.inventoryService.getAllInventory().subscribe((data) => {
      this.viewInventory = data;
      this.setProdctExpiredDate();
    });
  }
}
