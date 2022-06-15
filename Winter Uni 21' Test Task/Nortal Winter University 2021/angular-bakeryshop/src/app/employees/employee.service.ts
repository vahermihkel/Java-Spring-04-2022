import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) {}

  getEmployees(): Observable<any> {
    return this.http.get<any>("https://reqres.in/api/users"); // TODO: Load data from backend service
  }
}

// const [employees, setEmployees] = useState([]);

// fetch("https://reqres.in/api/users")
//  .then(res => res.json())
//  .then(empFromBackend => {
//     setEmployees(empFromBackend.data);
//})