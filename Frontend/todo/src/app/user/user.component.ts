import {
  Component,
  computed,
  EventEmitter,
  input,
  Input,
  signal,
  Output,
  output,
} from '@angular/core';
import { DUMMY_USERS } from '../dummy_users';

const randomIdx = Math.floor(Math.random() * DUMMY_USERS.length);

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [],
  templateUrl: './user.component.html',
  styleUrl: './user.component.css',
})
export class UserComponent {
  @Input({ required: true }) id!: string;
  @Input({ required: true }) avatar!: string;
  @Input({ required: true }) name!: string;
  @Output() select = new EventEmitter<string>();
  // select = output<string>();
  // avatar = input.required<string>();
  // name = input.required<string>();
  // selectedUser = signal(DUMMY_USERS[randomIdx]);
  userImg = computed(() => '../' + this.avatar);
  // get userImg() {
  //   return this.avatar;
  // }

  onSelectUser() {
    this.select.emit(this.id);
  }
}
