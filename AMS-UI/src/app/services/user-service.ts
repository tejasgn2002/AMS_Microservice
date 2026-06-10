import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs/internal/observable/throwError';
import { catchError } from 'rxjs/internal/operators/catchError';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  postUserLogin(data: any) {
    return this.http.post('http://localhost:8765/user/login', data).pipe(
      catchError((error) => {
        let message = 'Login failed. Please try again later.';
        if(error.status === 401){
          message = 'Invalid username or password. Please try again.';
        }
        else if(error.status === 500){
          message = 'Server error. Please try again later.';
        }
        return throwError(() => new Error(message));
      })
    );
  }

  postUserRegistration(data: any) {
    return this.http.post('http://localhost:8765/user/register', data).pipe(
      catchError((error) => { 
        let message = 'Registration failed. Please try again later.';
        if(error.status === 400){
          message = 'Invalid registration data. Please check your input and try again.';
        }
        else if(error.status === 500){
          message = 'Server error. Please try again later.';
        }
        else if(error.status === 409){
          message = error.error.error;
        }
        return throwError(() => new Error(message));
      })
    );
  }

  checkUsernameAvailability(username: any) {
    return this.http.get(`http://localhost:8765/user/check-username?username=${username}`).pipe(
      catchError((error) => {
        let message = 'Could not check username availability. Please try again later.';
        if(error.status === 500){
          message = 'Server error. Please try again later.';
        }
        return throwError(() => new Error(message));
      })
    );
  }

}
