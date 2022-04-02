import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddressService } from 'src/app/services/address/address.service';
import { CustomerService } from 'src/app/services/staff/customer/customer.service';
import { Order } from 'src/app/services/staff/order/order';
import { OrderService } from 'src/app/services/staff/order/order.service';

@Component({
  selector: 'app-view-customer',
  templateUrl: './view-customer.component.html',
  styleUrls: ['./view-customer.component.css']
})
export class ViewCustomerComponent implements OnInit {

  public viewcustomer?:any=[];

  public display:boolean=false;

  public order : any;
  public phn : any;
  public addr : any;

  constructor(private customerService:CustomerService, private orderService : OrderService, private addrServ : AddressService, private router : Router) { }

  ngOnInit():void {
      
  }

  getCustomerById(id:any){
    this.customerService.getCustomerById(id.phoneNumber).subscribe( response => {
      this.viewcustomer = response;
      this.display=true;
      console.log(this.viewcustomer);
      this.phn = id.phoneNumber;
      localStorage.setItem('phoneNo',id.phoneNumber)
      console.log(id.phoneNumber)
  },
  error=>{
    this.display=false;
    window.alert(error.error)
  });
}

  addorderbutton() {
    this.order=new Order();
    this.addrServ.get(this.phn).subscribe((res)=>{
      this.addr = res[0].id;
      console.log(this.addr);
      this.orderService.addOrder(this.phn,this.addr,this.order).subscribe(data=>{
        this.router.navigate(["add-order",this.phn]);
    })
    })
    
  }
}
