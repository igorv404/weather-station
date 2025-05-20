import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {AuthFormComponent} from '../components/auth-form/auth-form.component';

@Component({
  selector: 'app-registration',
  imports: [
    ReactiveFormsModule,
    AuthFormComponent
  ],
  templateUrl: './registration.component.html',
  styleUrl: './registration.component.scss'
})
export class RegistrationComponent {
  protected authForm: FormGroup = new FormGroup({
    email: new FormControl('', [Validators.email, Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  register() {
    console.log('Registration submitted', this.authForm.value);
  }
}
