import {Component, OnInit} from '@angular/core';
import {UserService} from '../service/user.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-header',
  styleUrls: ['../style/core.style.scss'],
  templateUrl: '../view/header.html'
})

export class HeaderComponent implements OnInit {

  email: string = '';
  password: string = '';
  isLogin: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
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
          this.isLogin = true;
        },
        error => {
        });
    });
  }

  shareMovie() {
    this.router.navigate(['/share']);
  }

  logout() {
    this.userService.logout().then(res => {
      this.isLogin = false;
    });
  }

}
