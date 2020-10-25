import {AfterViewInit, Component, OnInit} from '@angular/core';
import {HomeService} from '../service/home.service';
import {Video} from '../models/video.model';
import {VoteTypeEnum} from '../core/enumerations/vote-type.enum';

@Component({
    selector: 'app-homepage',
    templateUrl: '../view/home.view.html',
    styleUrls: ['../style/home.style.scss']
})

export class HomeComponent implements OnInit {

  constructor(private homeService: HomeService) {
  }

  videoData: Video[];
  totalPage: number = 0;
  voteUp: VoteTypeEnum = VoteTypeEnum.UP;
  voteDown: VoteTypeEnum = VoteTypeEnum.DOWN;

  voteVideo(id: number, type: VoteTypeEnum) {
    this.homeService.voteVideo(id, type).then(res => {
      let itemIndex = this.videoData.findIndex(item => item.id == res.data.id);
      this.videoData[itemIndex] = res.data;
    })
  }

  ngOnInit(): void {
    this.homeService.getAllVideo(1, 5).then(res => {
      this.videoData = (res.data.content as Video[]);
      this.totalPage = res.data.totalPages;
    });
  }

}
