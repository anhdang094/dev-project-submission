import {Component, ElementRef, Renderer2, ViewChild} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../models/user.model';

@Component({
    selector: 'app-header',
    styleUrls: ['../style/core.style.scss'],
    templateUrl: '../view/header.html'
})

export class HeaderComponent {

    constructor(private renderer: Renderer2, private route: ActivatedRoute, private router: Router) {
    }

}
