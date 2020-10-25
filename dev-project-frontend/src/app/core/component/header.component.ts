import {Component} from '@angular/core';
import {UserService} from '../service/user.service';

@Component({
  selector: 'app-header',
  styleUrls: ['../style/core.style.scss'],
  templateUrl: '../view/header.html'
})

export class HeaderComponent {

  email: string = '';
  password: string = '';

  constructor(private userService: UserService) {
  }


  login() {
    this.userService.register({
      email: this.email,
      password: this.password,
    }).then(res => {
      this.userService.login({
        email: this.email,
        password: this.password,
      }).subscribe(
        (res: any) => {
          console.log(res.headers.get('Authorization'));
        },
        error => {
        });
    });
  }

}
