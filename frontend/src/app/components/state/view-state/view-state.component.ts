import { Component, OnInit } from '@angular/core';
import { CountryService } from 'src/app/services/country/country.service';
import { StateService } from 'src/app/services/state/state.service';

@Component({
  selector: 'app-view-state',
  templateUrl: './view-state.component.html',
  styleUrls: ['./view-state.component.css']
})
export class ViewStateComponent implements OnInit {

  public country : any=[];
  public state : any=[];
  public display:boolean=false;
  constructor(private service : StateService, private countryService : CountryService) { }

  ngOnInit(): void {
    this.countryService.get().subscribe((res)=>{
      this.country = res;
  });
  }

  getState(country:any) {
    this.service.get(country.id).subscribe((res)=>{
      this.state = res;
      this.display=true;
    })
  }
}
