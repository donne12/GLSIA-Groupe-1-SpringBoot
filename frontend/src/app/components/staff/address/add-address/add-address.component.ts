import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AddressService } from 'src/app/services/address/address.service';
import { CountryService } from 'src/app/services/country/country.service';
import { StateService } from 'src/app/services/state/state.service';

@Component({
  selector: 'app-add-address',
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.css']
})
export class AddAddressComponent implements OnInit {

  public id : any;
  public state  :any=[];
  public country : any=[];
  constructor(private router : Router, private route: ActivatedRoute, private stateser : StateService, private countSer  :CountryService, private addressSer : AddressService) { 
    
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    console.log(this.id);
    this.countSer.get().subscribe((res)=>{
      this.country = res;
    })
  }

  getstate(count:any) {
    this.stateser.get(count).subscribe((res)=>{
      this.state = res;
    })
  }
  addaddress(value:any) {
    value.pinCode = Number(value.pinCode);
    this.addressSer.create(value, this.id as any as number).subscribe((res)=>{
      window.alert(res);
      this.gotoList();
    },
    error => window.alert(error.error)
    );

  }
  gotoList() {
    this.router.navigate(['/viewcustomer']);
  }
}
