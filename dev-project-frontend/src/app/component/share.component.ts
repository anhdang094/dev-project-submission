import {Component, ViewChild} from '@angular/core';
import {VideoService} from '../service/video.service';
import {ActivatedRoute, Router} from '@angular/router';
declare var $:any;

@Component({
  selector: 'app-share',
  templateUrl: '../view/share.view.html',
  styleUrls: ['../style/share.style.scss']
})

export class ShareComponent {

  constructor(private route: ActivatedRoute, private router: Router, private videoService: VideoService) {
  }

  url: string = '';

  submitShareMovie() {
    this.videoService.shareVideo({url: this.url}).then(res => {
      this.openNotice();
    });
  }

  openNotice() {
    $("#noticeModal").modal('show');
  }

  closeNotice() {
    $("#noticeModal").modal('hide');
  }

  goToHomePage() {
    this.closeNotice();
    this.router.navigate(['']);
  }

}
