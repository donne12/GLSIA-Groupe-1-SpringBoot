import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-indexcomponent',
  templateUrl: './indexcomponent.component.html',
  styleUrls: ['./indexcomponent.component.css']
})
export class IndexcomponentComponent implements OnInit {

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  onLogin(){
    this.router.navigate(['login']);
  }

}
