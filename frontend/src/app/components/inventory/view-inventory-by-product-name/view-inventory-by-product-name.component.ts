import { Component, OnInit } from '@angular/core';
import { InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-view-inventory-by-product-name',
  templateUrl: './view-inventory-by-product-name.component.html',
  styleUrls: ['./view-inventory-by-product-name.component.css']
})
export class ViewInventoryByProductNameComponent implements OnInit {

  public inventory : any=[];
  public display:boolean=false;
  constructor(private inventService : InventoryService) { }

  ngOnInit(): void {
  }

  getInventory(value:any) {
    this.inventService.getInventoryByName(value.name).subscribe((res)=>{
      console.log(res);
      this.inventory = res;
      if(this.inventory.length>0)
      {
      this.display=true;
    }});
  }
}
