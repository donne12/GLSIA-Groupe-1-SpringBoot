import { VendorDetailsComponent } from '../vendor-details/vendor-details.component';
import { Observable } from "rxjs";
import { VendorService } from "../../../vendor.service";
import { Vendor } from "../../../vendor";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: "app-vendor-list",
  templateUrl: "./vendor-list.component.html",
  styleUrls: ["./vendor-list.component.css"]
})
export class VendorListComponent implements OnInit {
  vendors: Observable<Vendor[]> | any;

  constructor(private vendorService: VendorService,
    private router: Router) {}

  ngOnInit() {
    this.reloadData();
  }

  reloadData() {
    this.vendors = this.vendorService.getVendorsList();
  }

  deleteVendor(id: number) {
    this.vendorService.deleteVendor(id)
      .subscribe(
        data => {
          console.log(data);
          window.alert(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  vendorDetails(id: number){
    this.router.navigate(['details', id]);
  }

  updateVendor(id: number){
    this.router.navigate(['update', id]);
  }
}
