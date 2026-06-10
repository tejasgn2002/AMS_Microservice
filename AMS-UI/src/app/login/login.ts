import { JsonPipe, NgIf } from '@angular/common';
import { ChangeDetectorRef, Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterLink } from "@angular/router";
import { UserService } from '../services/user-service';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink, NgIf],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  loginForm:any;
  errormessage:string = '';
  constructor(private fb:FormBuilder,private userservice:UserService,private changeDetect:ChangeDetectorRef){}

  ngOnInit(){
    this.loginForm = this.fb.group({
      username:['',Validators.required],
      password:['',Validators.required]
    })
  }

  onSubmit(){
    this.userservice.postUserLogin(this.loginForm.value).subscribe({
      next:(response:any)=>{
        this.errormessage = '';
        this.changeDetect.detectChanges();
        console.log(response.message);
        alert(response.message);
      },
      error:(error)=>{
        this.errormessage = error.message;
        this.changeDetect.detectChanges();
        console.error(error.message);
      }
    })
  }
}
