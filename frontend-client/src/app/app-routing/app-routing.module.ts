import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { HomeComponent } from '../home/home.component';
import { ProfileComponent } from '../profile/profile.component';
import { ProductComponent } from '../product/product.component';
import { OrdersComponent } from '../orders/orders.component';
import { OrderComponent } from '../order/order.component';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'edit-profile', component: EditProfileComponent },
  { path: 'product/:id', component: ProductComponent },
  { path: 'orders', component: OrdersComponent },
  { path: 'order/:id', component: OrderComponent },
  { path: 'logout', component: LoginComponent },
  { path: '**', component: LoginComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  declarations: [],
})
export class AppRoutingModule {}
