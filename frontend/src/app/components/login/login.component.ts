import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  constructor(
    private router: Router,
    private employeeService: EmployeeService
  ) {}

  ngOnInit(): void {}

  onLogin(credential: any) {
    if (
      credential.email == 'admin@gmail.com' &&
      credential.password == 'admin'
    ) {
      window.alert("T'es pas du coté admin frère !");
      // this.router.navigate(['admin']);
    } else if (
      credential.email == 'admin@gmail.com' &&
      credential.password != 'admin'
    ) {
      window.alert("Connexion à l'admin échouée.");
    } else {
      this.employeeService.loginValidation(credential).subscribe((data) => {
        if (data == 'true') {
          window.alert('Connexion réussie. Bienvenue!');
          this.router.navigate(['staff']);
        } else {
          window.alert('Echec de la connexion.');
        }
      });
    }
  }
}
