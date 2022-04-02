import { Component, OnInit } from '@angular/core';
import { InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-view-inventory-by-high-price',
  templateUrl: './view-inventory-by-high-price.component.html',
  styleUrls: ['./view-inventory-by-high-price.component.css']
})
export class ViewInventoryByHighPriceComponent implements OnInit {

  public inventory : any=[];
  constructor(private inventoryService : InventoryService) { }

  ngOnInit(): void {
    this.inventoryService.getInventoryByHighPrice().subscribe((res)=>{
      this.inventory = res;
    });
  }

}
