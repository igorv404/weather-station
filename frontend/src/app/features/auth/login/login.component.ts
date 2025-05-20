import { Component } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthFormComponent} from '../components/auth-form/auth-form.component';

@Component({
  selector: 'app-login',
  imports: [
    AuthFormComponent
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  protected authForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  login() {
    console.log('Registration submitted', this.authForm.value);
  }
}
