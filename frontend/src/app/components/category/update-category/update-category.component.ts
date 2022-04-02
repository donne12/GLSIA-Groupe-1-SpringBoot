import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Category, CategoryService } from 'src/app/services/category/category.service';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent implements OnInit {

  id?:any;
  category:Category=new Category;
  constructor(private router:Router,private categoryService:CategoryService) { 
    const navigation = this.router.getCurrentNavigation();
    const state = navigation?.extras.state as {
      id : any
      
    };
    this.id = state.id;
  
  }

  ngOnInit(): void {
    this.categoryService.getCategoryById(this.id).subscribe(data=>{
      this.category=data;
    },
    error=>window.alert(error.error)
    );
  }

  updateCategory(category : Category) {
   
    this.categoryService.updateCategory(this.id,category).subscribe((response) => {
      window.alert(response);
      this.router.navigate(['/viewCategory']);
    })
  }
}
