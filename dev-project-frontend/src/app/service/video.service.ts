import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {HttpService} from '../core/service/http.service';

@Injectable()
export class VideoService extends HttpService {

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }

  public getAllVideo(page: number, pageSize: number): Promise<any> {
    return this.getAPI('/api/videos?page=' + page + "&pageSize=" + pageSize);
  }

  public voteVideo(id: number, type: number): Promise<any> {
    return this.putAPI('/api/videos/' + id + "/vote?type=" + type, null);
  }

  public shareVideo(data: any): Promise<any> {
    return this.postAPI('/api/videos', data);
  }

}
