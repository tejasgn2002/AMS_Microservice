import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-registration',
  imports: [RouterLink],
  templateUrl: './registration.html',
  styleUrl: './registration.css',
})
export class Registration {
  registrationForm: any;

  constructor(private fb: FormBuilder) {
    this.registrationForm = this.fb.group({
      name: [''],
      username: [''],
      password: [''],
      customerCategory: [''],
      phoneNumber: [''],
      email: [''],
      address: [''],
      dateOfBirth: ['']
    });
  }
}
