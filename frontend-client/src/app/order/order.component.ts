import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  order = null;
  grandTotal = 0;
  discountAmount = 0;
  constructor(
    private orderService: OrderService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe((param) => {
      this.orderService.getOrder(param.get('id')).subscribe((res) => {
        this.order = res;
        if (!(this.order.totalPrice == 0 && this.order.discount == 0)) {
          this.discountAmount = this.order.totalPrice / this.order.discount;
          this.grandTotal = res.totalAmount - this.discountAmount;
        }
      });
    });
  }
}
