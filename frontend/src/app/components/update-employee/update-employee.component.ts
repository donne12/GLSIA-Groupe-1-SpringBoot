import { Component, OnInit } from '@angular/core';

import { ActivatedRoute, Router } from '@angular/router';
import { Employee } from 'src/app/services/employee/employee';
import { EmployeeService } from 'src/app/services/employee/employee.service';


@Component({
  selector: 'app-update-employee',
  templateUrl: './update-employee.component.html',
  styleUrls: ['./update-employee.component.css']
})
export class UpdateEmployeeComponent implements OnInit {

  id: number | any;
  employee: Employee | any;

  constructor(private route: ActivatedRoute,private router: Router,
    private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employee = new Employee();

    this.id = this.route.snapshot.params['id'];
    
    this.employeeService.getEmployee(this.id)
      .subscribe(data => {
       
        this.employee = data;
      }, error => console.log(error));
  }

  updateEmployee(employee:Employee) {
   
    this.employeeService.updateEmployee(this.id, employee)
      .subscribe(data => {
      window.alert(data);
        this.employee = new Employee();
        this.gotoList();
      }, error => window.alert(error.error));
  }

  gotoList() {
    this.router.navigate(['/employeeList']);
  }
 
}
