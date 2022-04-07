import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service';
import { ProfileInjectService } from '../profile-inject.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css'],
})
export class OrdersComponent implements OnInit {
  orders = [];
  profile = null;
  constructor(
    private orderService: OrderService,
    private profileService: ProfileInjectService
  ) {}

  ngOnInit() {
    //this.profileService.getProfile().phoneNumber
    this.orderService
      .getOrders(4000)
      .subscribe((res) => {
        this.orders = res.orderList;
        console.log(res);
      });
  }
}
