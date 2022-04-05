import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee/employee.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './loginAdmin.component.html',
  styleUrls: ['./loginAdmin.component.css'],
})
export class LoginComponentAdmin implements OnInit {
  constructor(
    private router: Router,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {}

  onLogin(credential: any) {
    console.log(credential);
    if (credential['email'] == '' || credential['password'] == '') {
      window.alert("Met tes identifiants d'abord stp 🙂!");
    } else {
      this.employeeService
        .loginValidationAdmin(credential)
        .subscribe((data) => {
          if (data && (credential['email'] == 'admin@gmail.com' && credential['password'] == 'admin')) {
            console.log(data);
            window.alert('Connexion réussie. Bienvenue Admin!');
            this.router.navigate(['admin']);
          } else {
       
            window.alert("Connexion à l'admin échouée.");
          }
        });
    }
  }
}
