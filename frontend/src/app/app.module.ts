import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddCategoryComponent } from './components/category/add-category/add-category.component';
import { ViewCategoryComponent } from './components/category/view-category/view-category.component';
import { ManageCategoryComponent } from './components/category/manage-category/manage-category.component';
import { UpdateCategoryComponent } from './components/category/update-category/update-category.component';
import { AddProductComponent } from './components/product/add-product/add-product.component';
import { ViewProductComponent } from './components/product/view-product/view-product.component';
import { ManageProductComponent } from './components/product/manage-product/manage-product.component';
import { UpdateProductComponent } from './components/product/update-product/update-product.component';
import { CreateVendorComponent } from './components/vendor/create-vendor/create-vendor.component';
import { VendorDetailsComponent } from './components/vendor/vendor-details/vendor-details.component';
import { VendorListComponent } from './components/vendor/vendor-list/vendor-list.component';
import { UpdateVendorComponent } from './components/vendor/update-vendor/update-vendor.component';
import { CategoryService } from './services/category/category.service';
import { ProductService } from './services/product/product.service';
import { InventoryService } from './services/inventory/inventory.service';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminComponent } from './components/admin/admin/admin.component';
import { AddInventoryComponent } from './components/inventory/add-inventory/add-inventory.component';
import { ViewInventoryComponent } from './components/inventory/view-inventory/view-inventory.component';
import { ManageInventoryComponent } from './components/inventory/manage-inventory/manage-inventory.component';
import { ViewInventoryByIdComponent } from './components/inventory/view-inventory-by-id/view-inventory-by-id.component';
import { ViewInventoryByProductNameComponent } from './components/inventory/view-inventory-by-product-name/view-inventory-by-product-name.component';
import { ViewInventoryByQuantityComponent } from './components/inventory/view-inventory-by-quantity/view-inventory-by-quantity.component';
import { ViewInventoryByLowPriceComponent } from './components/inventory/view-inventory-by-low-price/view-inventory-by-low-price.component';
import { ViewInventoryByHighPriceComponent } from './components/inventory/view-inventory-by-high-price/view-inventory-by-high-price.component';
import { ViewInventoryByPriceComponent } from './components/inventory/view-inventory-by-price/view-inventory-by-price.component';
import { ViewInventoryByDateComponent } from './components/inventory/view-inventory-by-date/view-inventory-by-date.component';
import { DatePipe } from '@angular/common';
import { DashboardComponent } from './components/staff/dashboard/dashboard.component';
import { AddCustomerComponent } from './components/staff/customer/add-customer/add-customer.component';
import { CreateEmployeeComponent } from './components/create-employee/create-employee.component';
import { UpdateEmployeeComponent } from './components/update-employee/update-employee.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { EmployeeDetailsComponent } from './components/employee-details/employee-details.component';
import { ViewCustomerComponent } from './components/staff/customer/view-customer/view-customer.component';
import { AddCountryComponent } from './components/country/add-country/add-country.component';
import { ViewCountryComponent } from './components/country/view-country/view-country.component';
import { AddStateComponent } from './components/state/add-state/add-state.component';
import { ViewStateComponent } from './components/state/view-state/view-state.component';
import { AddAddressComponent } from './components/staff/address/add-address/add-address.component';
import { AddOrderComponent } from './components/staff/order/add-order/add-order.component';
import { ProcessOrderComponent } from './components/staff/order/process-order/process-order.component';
import { IndexcomponentComponent } from './components/indexcomponent/indexcomponent.component';
import { LoginComponent } from './components/login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    AddCategoryComponent,
    ViewCategoryComponent,
    ManageCategoryComponent,
    UpdateCategoryComponent,
    AddProductComponent,
    ViewProductComponent,
    ManageProductComponent,
    UpdateProductComponent,
    AdminComponent,
    CreateVendorComponent,
    VendorDetailsComponent,
    VendorListComponent,
    UpdateVendorComponent,
    AddInventoryComponent,
    ViewInventoryComponent,
    ManageInventoryComponent,
    ViewInventoryByIdComponent,
    ViewInventoryByProductNameComponent,
    ViewInventoryByQuantityComponent,
    ViewInventoryByLowPriceComponent,
    ViewInventoryByHighPriceComponent,
    ViewInventoryByPriceComponent,
    ViewInventoryByDateComponent,
    DashboardComponent,
    AddCustomerComponent,
    CreateEmployeeComponent,
    UpdateEmployeeComponent,
    EmployeeListComponent,
    EmployeeDetailsComponent,
    ViewCustomerComponent,
    AddCountryComponent,
    ViewCountryComponent,
    AddStateComponent,
    ViewStateComponent,
    AddAddressComponent,
    AddOrderComponent,
    ProcessOrderComponent,
    IndexcomponentComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
    
  ],
  providers: [CategoryService,ProductService,InventoryService,DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
