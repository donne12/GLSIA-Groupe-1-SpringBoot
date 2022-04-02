import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Inventory, InventoryService } from 'src/app/services/inventory/inventory.service';
import { DatePipe, formatDate } from '@angular/common';

@Component({
  selector: 'app-view-inventory',
  templateUrl: './view-inventory.component.html',
  styleUrls: ['./view-inventory.component.css']
})
export class ViewInventoryComponent implements OnInit {

  public inventory:any=[];
  public today:Date=new Date();
  public date : any;
  constructor(private inventoryService : InventoryService,private router:Router, private datePipe : DatePipe) {
    this.date = this.formatDate();
   }

  ngOnInit(): void {

    console.log(this.date);
    console.log(this.today)
    this.inventoryService.getAllInventory().subscribe((res)=>{
      this.inventory = res;
      console.log(this.inventory)
    });
    
  }

  formatDate() {
    var d = new Date(),
        month = '' + (d.getMonth() + 1),
        day = '' + d.getDate(),
        year = d.getFullYear();

    if (month.length < 2) 
        month = '0' + month;
    if (day.length < 2) 
        day = '0' + day;

    return [year, month, day].join('-');
}



}
