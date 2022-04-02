import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { InventoryService } from 'src/app/services/inventory/inventory.service';

@Component({
  selector: 'app-manage-inventory',
  templateUrl: './manage-inventory.component.html',
  styleUrls: ['./manage-inventory.component.css']
})
export class ManageInventoryComponent implements OnInit {

  public inventory:any=[];

  constructor(private inventoryService : InventoryService, private router : Router) { }

  ngOnInit(): void {

    this.inventoryService.getAllInventory().subscribe((res)=>{
      this.inventory = res;
    });
  }

  delete(id:number) {
    this.inventoryService.deleteInventory(id).subscribe((res)=>{
      window.alert(res);
      this.router.navigate(["manageinventory"]);
    });
  }

}
