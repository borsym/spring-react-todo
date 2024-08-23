import { Component, DestroyRef, inject, OnInit } from '@angular/core';
import {
  ReactiveFormsModule,
  FormControl,
  FormGroup,
  Validators,
  AbstractControl,
} from '@angular/forms';
import { debounceTime, of } from 'rxjs';

function mustContainQuestionMark(control: AbstractControl) {
  if (control.value.includes('?')) {
    return null;
  }

  return { doesNotContainQuestionMark: true };
}

function usernameIsUnique(control: AbstractControl) {
  if (control.value !== 'mate') {
    return of(null); // observablet csinal belole
  }

  return of({ notUnique: true });
}

let initialUsername = '';
const savedForm = window.localStorage.getItem('saved-login-form');
if (savedForm) {
  const loadedForm = JSON.parse(savedForm);
  initialUsername = loadedForm.username;
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent implements OnInit {
  private destroyRef = inject(DestroyRef);

  form = new FormGroup({
    username: new FormControl(initialUsername, {
      validators: [Validators.required, Validators.minLength(6)],
      asyncValidators: [usernameIsUnique],
    }),
    password: new FormControl('', {
      validators: [
        Validators.required,
        Validators.minLength(6),
        mustContainQuestionMark,
      ],
    }),
  });

  get usernameIsInValid() {
    return (
      this.form.controls.username.dirty &&
      this.form.controls.username.touched &&
      this.form.controls.username.invalid
    );
  }

  get passwordIsInvalid() {
    return (
      this.form.controls.password.dirty &&
      this.form.controls.password.touched &&
      this.form.controls.password.invalid
    );
  }

  ngOnInit(): void {
    const subscription = this.form.valueChanges
      .pipe(debounceTime(500))
      .subscribe({
        next: (value) => {
          window.localStorage.setItem(
            'saved-login-form',
            JSON.stringify({ username: value.username })
          );
        },
      });
    this.destroyRef.onDestroy(() => subscription.unsubscribe());
  }
  onSubmit() {
    console.log('hello  ');
    console.log(this.form);
    const username = this.form.value.username;
    const pass = this.form.value.password;
    console.log(username, pass);
  }
}
