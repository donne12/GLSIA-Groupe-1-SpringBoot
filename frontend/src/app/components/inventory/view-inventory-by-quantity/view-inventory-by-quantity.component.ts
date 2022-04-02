import { Component, OnInit } from '@angular/core';
import { InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-view-inventory-by-quantity',
  templateUrl: './view-inventory-by-quantity.component.html',
  styleUrls: ['./view-inventory-by-quantity.component.css']
})
export class ViewInventoryByQuantityComponent implements OnInit {

  public inventory : any=[];
  public display:boolean=false;
  constructor(private inventService : InventoryService) { }

  ngOnInit(): void {
  }

  getInventory(value:any) {
    this.inventService.getInventoryByQuantity(Number(value.quantity)).subscribe((res)=>{
      console.log(res);
      this.inventory = res;
      if(this.inventory.length>0)
      {
      this.display=true;
    }
    });
  }
}
