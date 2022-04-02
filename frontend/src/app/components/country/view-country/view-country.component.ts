import { Component, OnInit } from '@angular/core';
import { CountryService } from 'src/app/services/country/country.service';

@Component({
  selector: 'app-view-country',
  templateUrl: './view-country.component.html',
  styleUrls: ['./view-country.component.css']
})
export class ViewCountryComponent implements OnInit {

  public country : any=[]
  constructor(private service : CountryService) { }

  ngOnInit(): void {
    this.service.get().subscribe((res)=>{
      this.country = res;
    })
  }


}
