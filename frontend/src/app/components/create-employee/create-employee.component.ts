
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Employee } from 'src/app/services/employee/employee';
import { EmployeeService } from 'src/app/services/employee/employee.service';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {

  submitted = false;
  constructor(private employeeService: EmployeeService,
    private router: Router) { }

  ngOnInit() {
  }



  addEmployee(employee:Employee){
    this.employeeService
    .createEmployee(employee).subscribe(data => {
     window.alert(data);
      this.gotoList();
    },
    error => window.alert(error.error)
    );
  }

  gotoList() {
    this.router.navigate(['/employeeList']);
  }
}
