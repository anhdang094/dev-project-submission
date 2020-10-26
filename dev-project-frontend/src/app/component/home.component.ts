import {AfterViewInit, Component, OnInit} from '@angular/core';
import {VideoService} from '../service/video.service';
import {Video} from '../models/video.model';
import {VoteTypeEnum} from '../core/enumerations/vote-type.enum';

@Component({
    selector: 'app-homepage',
    templateUrl: '../view/home.view.html',
    styleUrls: ['../style/home.style.scss']
})

export class HomeComponent implements OnInit {

  constructor(private videoService: VideoService) {
  }

  videoData: Video[];
  totalPage: number = 0;
  voteUp: VoteTypeEnum = VoteTypeEnum.UP;
  voteDown: VoteTypeEnum = VoteTypeEnum.DOWN;
  page: number = 1;
  pageSize: number = 5;

  voteVideo(id: number, type: VoteTypeEnum) {
    this.videoService.voteVideo(id, type).then(res => {
      let itemIndex = this.videoData.findIndex(item => item.id == res.data.id);
      this.videoData[itemIndex].voteUp = res.data.voteUp;
      this.videoData[itemIndex].voteDown = res.data.voteDown;
    })
  }

  moreMovie(): void {
    this.page = this.page + 1;
    this.videoService.getAllVideo(this.page, this.pageSize).then(res => {
      this.videoData.push.apply(this.videoData, res.data.content as Video[]);
    });
  }

  ngOnInit(): void {
    this.videoService.getAllVideo(1, this.pageSize).then(res => {
      this.videoData = (res.data.content as Video[]);
      this.totalPage = res.data.totalPages;
    });
  }

}
