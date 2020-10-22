import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class HomeService extends HttpService {

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }

  public uploadAvatar(file): Promise<any> {
    return this.postFile('/api/avatars', file);
  }

  public registerArtist(data: any): Promise<any> {
    return this.postAPI('/api/artists', data);
  }

  public updateArtist(data: any): Promise<any> {
    return this.putAPI('/api/artists', data);
  }

  public getAllArtist(page: number, pageSize: number, character: string): Promise<any> {
    return this.getAPI('/api/artists?page=' + page + "&pageSize=" + pageSize + "&character=" + character);
  }

  public getArtistById(id: number): Promise<any> {
    return this.getAPI('/api/artists/' + id);
  }

  public getNounc(id: number, address: string): Promise<any> {
    return this.getAPI('/authen/nounc/' + id + '?address=' + address);
  }

}
