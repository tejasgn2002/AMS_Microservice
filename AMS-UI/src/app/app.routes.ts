import { Routes } from '@angular/router';
import { Login } from './login/login';
import { Registration } from './registration/registration';

export const routes: Routes = [
    {path: '', component: Registration},
    {path: 'login', component: Login}
];
