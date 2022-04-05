import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
  selector: 'app-manage-product',
  templateUrl: './manage-product.component.html',
  styleUrls: ['./manage-product.component.css'],
})
export class ManageProductComponent implements OnInit {
  public viewProduct?: any = [];

  constructor(private router: Router, private productService: ProductService) {}

  ngOnInit(): any {
    this.productService.getAllProduct().subscribe((response) => {
      this.viewProduct = response;
    });
  }

  deleteProduct(id: number, categoryId: number) {
    if (confirm('Êtes-vous sûr(e) de vouloir supprimer ce produit?')) {
      this.productService
        .deleteProduct(id, categoryId)
        .subscribe((response) => {
          console.log(response);

          window.alert('Suppression faite avec succès.');
          this.router.navigate(['/viewProduct']);
        });
    } else {
      window.alert('Sage décision 😊👌.');
    }
  }

  updateProduct(id: number) {
    this.router.navigate(['updateProduct'], { state: { id: id } });
  }
}
