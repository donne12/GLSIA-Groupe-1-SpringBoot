import { Component, OnInit } from '@angular/core';
import { InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-view-inventory-by-low-price',
  templateUrl: './view-inventory-by-low-price.component.html',
  styleUrls: ['./view-inventory-by-low-price.component.css']
})
export class ViewInventoryByLowPriceComponent implements OnInit {

  public inventory : any=[];
  constructor(private inventoryService : InventoryService) { }

  ngOnInit(): void {
    this.inventoryService.getInventoryByLowPrice().subscribe((res)=>{
      this.inventory = res;
    });
  }

}
