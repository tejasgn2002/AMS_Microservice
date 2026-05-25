import { JsonPipe, NgClass, NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { validateDateOfBirth } from './custom-validator/dateOfBirth.validtor';
import { UserService } from '../services/user-service';

@Component({
  selector: 'app-registration',
  imports: [RouterLink, ReactiveFormsModule, JsonPipe, NgIf],
  templateUrl: './registration.html',
  styleUrl: './registration.css',
})
export class Registration {
  registrationForm: any;

  constructor(private fb: FormBuilder,private userService: UserService) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6), Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()-+])[A-Za-z\d!@#$%^&*()-+]{6,}$/)]],
      customerCategory: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: ['', [Validators.required, Validators.email]],
      address: ['',Validators.required],
      dateOfBirth: ['',Validators.required]
    },{ validators: validateDateOfBirth });
  }


  onSubmit() {
    console.log(this.registrationForm.value);
    this.userService.postUserRegistration(this.registrationForm.value).subscribe({
      next: (response: any) => {
        console.log('Registration successful:', response);
        alert(response.message)
      },
      error: (error) => {
        console.error(error);
      }
    });
  }



  get name() {
    return this.registrationForm.get('name');
  }

  get username() { 
    return this.registrationForm.get('username');
  }

  get password() {
    return this.registrationForm.get('password');
  }

  get customerCategory() { 
    return this.registrationForm.get('customerCategory');
  }
  get phoneNumber() {
    return this.registrationForm.get('phoneNumber');
  }
  get email() {
    return this.registrationForm.get('email');
  }
  get address() {
    return this.registrationForm.get('address');
  }
  get dateOfBirth() {
    return this.registrationForm.get('dateOfBirth');
  }
}
