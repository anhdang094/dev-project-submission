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
  isError: boolean = false;

  constructor(private route: ActivatedRoute, private router: Router, private userService: UserService) {
  }

  ngOnInit(): void {
    if (localStorage.getItem("Authorization")) {
      this.isLogin = true;
    }
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
          localStorage.setItem("Authorization", res.headers.get('Authorization'));
          this.isLogin = true;
        },
        error => {
          this.isError = true;
        });
    });
  }

  shareMovie() {
    this.router.navigate(['/share']);
  }

  logout() {
    localStorage.removeItem("Authorization");
    this.isLogin = false;
    this.userService.logout();
  }

}
