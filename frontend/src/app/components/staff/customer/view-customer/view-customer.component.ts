import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AddressService } from 'src/app/services/address/address.service';
import { CustomerService } from 'src/app/services/staff/customer/customer.service';
import { Order } from 'src/app/services/staff/order/order';
import { OrderService } from 'src/app/services/staff/order/order.service';

@Component({
  selector: 'app-view-customer',
  templateUrl: './view-customer.component.html',
  styleUrls: ['./view-customer.component.css'],
})
export class ViewCustomerComponent implements OnInit {
  public viewcustomer?: any = [];

  public display: boolean = false;

  public order: any;
  public phn: any;
  public addr: any;

  constructor(
    private customerService: CustomerService,
    private orderService: OrderService,
    private addrServ: AddressService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.customerService.getAllCustomer().subscribe((response) => {
      this.viewcustomer = response;
      this.display = true;
      console.log(this.viewcustomer);
    });
  }

  getCustomerById() {
    this.customerService.getAllCustomer().subscribe(
      (response) => {
        this.viewcustomer = response;
        this.display = true;
      },
      (error) => {
        this.display = false;
        window.alert(error.error);
      }
    );
  }

  addorderbutton(id: any) {
    this.order = new Order();
    this.addrServ.get(id).subscribe((res) => {
      console.log(res);
      localStorage.setItem('phoneNo',id)
      this.addr = res[0].id;
      this.orderService
        .addOrder(id, this.addr, this.order)
        .subscribe((data) => {
          console.log(data)
          this.router.navigate(['add-order', id]);
        });
    });
  }
}
