import { Vendor } from '../../../vendor';
import { Component, OnInit, Input } from '@angular/core';
import { VendorService } from '../../../vendor.service';
import { VendorListComponent } from '../vendor-list/vendor-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-vendor-details',
  templateUrl: './vendor-details.component.html',
  styleUrls: ['./vendor-details.component.css']
})
export class VendorDetailsComponent implements OnInit {

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

  list(){
    this.router.navigate(['/vendorList']);
  }
}
