import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeService } from './employee.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {
  employees: any = [];
  form: FormGroup;

  constructor(private employeeService: EmployeeService,
              private fb: FormBuilder) {
  }

  ngOnInit() {
    this.employeeService.getEmployees().subscribe(empFromBackend => {
      this.employees = empFromBackend.data;
    })
    this.initForm();
  }

  private initForm(): void {
    this.form = this.fb.group({ // TODO: Add validations
      id: ['', [Validators.required, Validators.pattern(/^[0-9]+$/)]],
      name: ['', [Validators.required, Validators.pattern(/^[a-zöäüõ -.A-ZÖÄÜÕ]+$/)]],
      email: ['', [Validators.required, Validators.email]],
      avatar: ['', [Validators.required]]
    });
  }

  addEmployee(): void {
    const newEmployee: any = {
      id: this.form.get('id').value,
      first_name: this.form.get('name').value.split(" ")[0],
      last_name: this.form.get('name').value.split(" ")[1],
      email: this.form.get('email').value,
      avatar: this.form.get('avatar').value
    };
    this.employees.push(newEmployee);
    this.initForm();
  }

  deleteEmployee(employee): void {
    // .delete // .remove <- ei ole
    // const index = this.employees.findIndex(emp => emp.id === employee.id);
    const index = this.employees.indexOf(employee);
    if (index >= 0) {
      this.employees.splice(index,1);
    }
  }
}
