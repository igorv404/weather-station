import { Component, EventEmitter, Input, Output } from '@angular/core';
import { InputText } from 'primeng/inputtext';
import { Password } from 'primeng/password';
import { Button } from 'primeng/button';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-auth-form',
  imports: [InputText, Password, Button, ReactiveFormsModule, RouterLink],
  templateUrl: './auth-form.component.html',
  styleUrl: './auth-form.component.scss'
})
export class AuthFormComponent {
  @Input({ required: true }) form!: FormGroup;
  @Input({ required: true }) formLabel!: string;
  @Input({ required: true }) submitLabel!: string;
  @Input({ required: true }) redirectHint!: string;
  @Input({ required: true }) redirectText!: string;
  @Input({ required: true }) redirectUrl!: string;
  @Output() submitted = new EventEmitter<void>();

  protected onSubmit() {
    if (this.form.valid) {
      this.submitted.emit();
    }
  }
}
