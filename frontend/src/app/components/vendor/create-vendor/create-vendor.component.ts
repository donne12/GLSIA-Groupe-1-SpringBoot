import { VendorService } from '../../../vendor.service';
import { Vendor } from '../../../vendor';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-vendor',
  templateUrl: './create-vendor.component.html',
  styleUrls: ['./create-vendor.component.css']
})
export class CreateVendorComponent implements OnInit {

  vendor: Vendor = new Vendor();
  submitted = false;

  constructor(private vendorService: VendorService,
    private router: Router) { }

  ngOnInit() {
  }

  newVendor(): void {
    this.submitted = false;
    this.vendor = new Vendor();
  }

  save() {
    this.vendorService
    .createVendor(this.vendor).subscribe(data => {
      console.log(data)
      window.alert(data);
      this.vendor = new Vendor();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/vendorList']);
  }
}
