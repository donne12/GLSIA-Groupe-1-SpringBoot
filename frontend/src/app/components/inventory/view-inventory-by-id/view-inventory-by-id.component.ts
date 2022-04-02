import { Component, OnInit } from '@angular/core';
import { Inventory, InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-view-inventory-by-id',
  templateUrl: './view-inventory-by-id.component.html',
  styleUrls: ['./view-inventory-by-id.component.css']
})
export class ViewInventoryByIdComponent implements OnInit {

  public inventory : any=[];

  constructor(private inventService : InventoryService) { }

  ngOnInit(): void {
  }

  getInventory(value:any) {
    this.inventService.getInventoryById(value.id).subscribe((res)=>{
      this.inventory = res;
    });
  }
}
