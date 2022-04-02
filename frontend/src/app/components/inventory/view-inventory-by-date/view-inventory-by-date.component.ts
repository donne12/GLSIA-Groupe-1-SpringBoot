import { Component, OnInit } from '@angular/core';
import { InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-view-inventory-by-date',
  templateUrl: './view-inventory-by-date.component.html',
  styleUrls: ['./view-inventory-by-date.component.css']
})
export class ViewInventoryByDateComponent implements OnInit {

  public inventory : any=[];
  public display:boolean=false;
  constructor(private inventService : InventoryService) { }

  ngOnInit(): void {
  }

  getInventory(uservalue : any) {
    this.inventService.getInventoryByDate(uservalue.start,uservalue.end).subscribe((res)=>{
      this.inventory = res;
      if(this.inventory.length>0)
      {
      this.display=true;
    }})
  }
}
