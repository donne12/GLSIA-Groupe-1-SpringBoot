import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category.service';
import { ProductService } from '../product.service';
import { ProfileInjectService } from '../profile-inject.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  name = '';
  categories = [
    { id: 1, name: 'Chocolates' },
    { id: 2, name: 'Electronics' },
  ];
  products = [];
  constructor(
    private profileService: ProfileInjectService,
    private categoryService: CategoryService,
    private productServic: ProductService
  ) {}

  ngOnInit() {
    this.name = 'Donné ESIAKU';
    //this.profileService.getProfile().name;
    /*this.categoryService.getCategories().subscribe((res) => {
      this.categories = res;
    });*/
  }
  categoryFetch(value) {
    this.productServic.getCategoryProducts(value).subscribe((res) => {
      console.log(res);
      this.products = res.productList;
    });
  }
}
