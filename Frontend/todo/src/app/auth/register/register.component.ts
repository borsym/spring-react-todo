import { Component } from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

let initialUsername = '';
let initialEmail = '';

function matchPasswords(controlName1: string, controlName2: string) {
  return (control: AbstractControl) => {
    const name1 = control.get(controlName1)?.value;
    const name2 = control.get(controlName2)?.value;
    if (name1 === name2) return null;
    return { valuesNotEqual: true };
  };
}
@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  form = new FormGroup({
    username: new FormControl(initialUsername, {
      validators: [Validators.minLength(6), Validators.required],
    }),
    email: new FormControl(initialEmail, {
      validators: [Validators.email, Validators.required],
    }),
    passwords: new FormGroup(
      {
        password: new FormControl('', {
          validators: [Validators.required],
        }),
        confirmPassword: new FormControl('', {
          validators: [Validators.required],
        }),
      },
      {
        validators: [matchPasswords('password', 'confirmPassword')],
      }
    ),
    role: new FormControl<'Dev' | 'lead'>('Dev', {
      validators: [Validators.required],
    }),
    team: new FormArray([
      new FormControl(false),
      new FormControl(false),
      new FormControl(false),
    ]),
  });

  onSubmit() {
    if (this.form.invalid) {
      console.log('INVALID FORM');
      return;
    }

    console.log(this.form);
  }
}
