import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Address } from 'src/app/services/address/address';
import {  AddressService } from 'src/app/services/address/address.service';
import { Customer, CustomerService } from 'src/app/services/staff/customer/customer.service';
import { Order } from 'src/app/services/staff/order/order';
import { OrderService } from 'src/app/services/staff/order/order.service';

@Component({
  selector: 'app-process-order',
  templateUrl: './process-order.component.html',
  styleUrls: ['./process-order.component.css']
})
export class ProcessOrderComponent implements OnInit {
  public order:Order|any 
  public orderDetail:Order|any
  public orderDetails:Order|any
  public phoneNumber:Number|any
  public address:Address|any
  public customer:Customer|any
  public num=5.5
  public url:String|any
  public shouldPdf:boolean=true;
  constructor(private orderService:OrderService,private router:Router,private addressService:AddressService,private customerService:CustomerService) { }
  ProcessOrderForm=new FormGroup({
    modeOfPayment:new FormControl('',Validators.required),
  })
  ngOnInit(): void {
    this.order=localStorage.getItem('order')
    this.orderDetail=JSON.parse(this.order)
    console.log(this.orderDetail)
    this.url='http://localhost:8083/api/get-bill/pdf/'+`${this.orderDetail.orderId}`
    this.phoneNumber=localStorage.getItem('phoneNo')
    console.log(this.phoneNumber)
    this.customerService.getCustomerById(this.phoneNumber).subscribe(data=>{
      this.customer=data
      console.log(this.customer)
      this.addressService.get(this.phoneNumber).subscribe(data=>{
        this.address=data
        console.log(this.address)
        console.log(this.address[0].addressLine)
        console.log(this.address[0].city)
        console.log(this.address[0].pinCode)
      })
    })
    
  }
  processOrder()
  {
    this.orderDetails=new Order();
    this.orderDetails.modeOfPayment=this.ProcessOrderForm.get('modeOfPayment')?.value;
    this.orderDetails.status="SUCCESS";
    this.orderDetails.tracking="DELIVERED";
    this.orderDetails.discount=this.orderDetail.discount
    this.orderDetails.totalPrice=this.orderDetail.totalPrice
    this.orderService.updateOrder(this.orderDetail.orderId,this.orderDetails).subscribe(response=>{
      console.log(response)
      window.alert(response)
      this.shouldPdf=false;
    })
  }

  get modeOfPayment(){
    return this.ProcessOrderForm.get('modeOfPayment');
  }

  
}
