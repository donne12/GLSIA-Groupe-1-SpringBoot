import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer, CustomerService } from 'src/app/services/staff/customer/customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {


  constructor(private customerService:CustomerService, private router : Router) { }

  ngOnInit(): void {
  }

  addCustomer(customer:Customer) {
    console.log(customer);
    this.customerService.createCustomer(customer).subscribe((response) => {
      window.alert(response);
      this.router.navigate(["addaddress",customer.phoneNumber]);
    });
  }



}
