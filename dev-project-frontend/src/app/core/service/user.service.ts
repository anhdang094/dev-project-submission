import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class UserService extends HttpService {

  constructor(httpClient: HttpClient) {
    super(httpClient);
  }

  public register(data: any): Promise<any> {
    return this.postAPI('/users/signup', data);
  }

  public login(data: any): Promise<any> {
    return this.postAPI('/login', data);
  }

}
