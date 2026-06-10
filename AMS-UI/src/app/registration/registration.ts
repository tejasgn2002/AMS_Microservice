import { NgIf } from '@angular/common';
import { ChangeDetectorRef, Component, ElementRef, ViewChild } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import {Router, RouterLink } from '@angular/router';
import { validateDateOfBirth } from './custom-validator/dateOfBirth.validtor';
import { UserService } from '../services/user-service';
import { debounceTime, distinct, distinctUntilChanged, fromEvent, pluck, switchMap } from 'rxjs';

@Component({
  selector: 'app-registration',
  imports: [RouterLink, ReactiveFormsModule, NgIf],
  templateUrl: './registration.html',
  styleUrl: './registration.css',
})
export class Registration {
  registrationForm: any;
  usernameAvailable: boolean= false;
  usernameCheckError!: string;

  constructor(private fb: FormBuilder,private userService: UserService,private route:Router
    ,private changeDetector:ChangeDetectorRef) {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6), Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()-+])[A-Za-z\d!@#$%^&*()-+]{6,}$/)]],
      customerCategory: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      email: ['', [Validators.required, Validators.email]],
      address: ['',Validators.required],
      dateOfBirth: ['',Validators.required]
    },{ validators: validateDateOfBirth });
  }

  @ViewChild('usernameInput') usernameInput!: ElementRef;

  ngAfterViewInit() {

    fromEvent(this.usernameInput.nativeElement, 'input').pipe(
      pluck('target', 'value'),
      debounceTime(1000),
      distinctUntilChanged(),
      switchMap((username) => this.userService.checkUsernameAvailability(username))
    ).subscribe({
      next: (response: any) => {
        if (response.available) {
          this.usernameAvailable = true;
        } else {
          this.usernameAvailable = false;
          this.username?.setErrors({ notAvailable: true });
        }
        this.changeDetector.detectChanges();
      },
      error: (error) => {
        console.error('Error checking username availability:', error);
        this.username?.setErrors({ checkFailed: true });
      }
    });

  }

  onSubmit() {
    console.log(this.registrationForm.value);
    this.userService.postUserRegistration(this.registrationForm.value).subscribe({
      next: (response: any) => {
        console.log('Registration successful:', response);
        alert(response.message);
        this.route.navigate(['/login']);
      },
      error: (error) => {
        console.error(error);
        alert(error.message);
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
  get phone() {
    return this.registrationForm.get('phone');
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
