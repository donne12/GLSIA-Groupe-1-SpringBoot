import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product, ProductService } from 'src/app/services/product/product.service';
import { OrderItem } from 'src/app/services/staff/order-item/order-item';
import { Order } from 'src/app/services/staff/order/order';
import { OrderService } from 'src/app/services/staff/order/order.service';


@Component({
  selector: 'app-add-order',
  templateUrl: './add-order.component.html',
  styleUrls: ['./add-order.component.css']
})
export class AddOrderComponent implements OnInit {
  AddOrderForm=new FormGroup({
    productName:new FormControl(''),
    quantity:new FormControl('')
  })
  constructor(private productService:ProductService,private orderService:OrderService,private router:Router, private route : ActivatedRoute) {
    this.id = this.route.snapshot.params['id'];
   }
  public productList:Observable<Product[]>|any
  public product:Product|any
  public order:Order|any
  public orderItem:OrderItem|any
  public orderId:Number|any
  public orderedItems:Observable<OrderItem[]>|any
  public length:number|any
  public id : any;
  public quantity:any;
  ngOnInit(): void {
    this.productService.getAllProduct().subscribe(data=>{
      console.log(data)
      this.productList=data
      console.log(this.AddOrderForm.get('productName')?.value)
    })
  }
  addOrderItem()
  {
    this.orderService.getOrderId(this.id).subscribe(data=>{
      console.log(data)
      this.orderId=data
      this.orderItem=new OrderItem()
      this.orderItem.quantity=this.AddOrderForm.get('quantity')?.value
      this.orderService.addOrderItem(this.orderId,this.product.id,this.orderItem).subscribe(data=>{
        console.log(data)
        window.alert(data)
        this.orderService.getOrderedItems(this.orderId).subscribe(data=>{
          this.orderedItems=data
          this.length=this.orderedItems.length
          console.log(data)
          this.orderService.getOrderById(this.orderId).subscribe(data=>{
            console.log(data)
            this.order=data
          })
        })
      })
    })
  }
  getavailability(id:any)
  {
    this.productService.getProductById(id).subscribe(data=>{
      console.log(data)
      this.product=data
    })
  }
  goToPayment()
  {
    localStorage.setItem("order",JSON.stringify(this.order))
    this.router.navigate(['process-order'])
  }
  setQuantity(){
     this.quantity=this.AddOrderForm.get('quantity')?.value;

  }
}
