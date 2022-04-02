import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category, CategoryService } from 'src/app/services/category/category.service';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent implements OnInit {

  constructor(private categoryService:CategoryService,private router:Router) { }

  ngOnInit(): void {
  }

  addCategory(category : Category) {
   
    this.categoryService.createCategory(category).subscribe((response) => {
      window.alert(response);
      this.router.navigate(['/viewCategory']);
    })
  }

}
