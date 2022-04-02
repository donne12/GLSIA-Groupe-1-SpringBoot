import { Component, OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category/category.service';

@Component({
  selector: 'app-view-category',
  templateUrl: './view-category.component.html',
  styleUrls: ['./view-category.component.css']
})
export class ViewCategoryComponent implements OnInit {

  public viewCategory?:any=[];


  constructor(private categoryService:CategoryService) { }

  ngOnInit():any {
    this.categoryService.getAllCategory().subscribe( response => {
      this.viewCategory = response;
     
  });
  }

}
