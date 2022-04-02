import { Component, OnInit } from '@angular/core';
import { Vendor } from '../../../vendor';
import { ActivatedRoute, Router } from '@angular/router';
import { VendorService } from '../../../vendor.service';

@Component({
  selector: 'app-update-vendor',
  templateUrl: './update-vendor.component.html',
  styleUrls: ['./update-vendor.component.css']
})
export class UpdateVendorComponent implements OnInit {

  id: number | any;
  vendor: Vendor | any;

  constructor(private route: ActivatedRoute,private router: Router,
    private vendorService: VendorService) { }

  ngOnInit() {
    this.vendor = new Vendor();

    this.id = this.route.snapshot.params['id'];
    
    this.vendorService.getVendor(this.id)
      .subscribe(data => {
        console.log(data)
        this.vendor = data;
      }, error => console.log(error));
  }

  updateVendor() {
    this.vendorService.updateVendor(this.id, this.vendor)
      .subscribe(data => {
        console.log(data);
        window.alert(data);
        this.vendor = new Vendor();
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateVendor();    
  }

  gotoList() {
    this.router.navigate(['/vendorList']);
  }
}
