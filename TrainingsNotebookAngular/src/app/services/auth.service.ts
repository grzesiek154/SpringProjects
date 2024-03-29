import { HttpClient } from '@angular/common/http';
import { Injectable, Output } from '@angular/core';
import { EventEmitter } from '@angular/core';
import { LocalStorageService } from 'ngx-webstorage';
import { Observable, throwError } from 'rxjs';
import { LoginRequestPayload } from '../models/auth/login-request-payload';
import { SignupRequestPayload } from '../models/auth/signup-request-payload';
import { map, tap } from 'rxjs/operators';
import { LoginResponse } from '../models/auth/login-response-payload';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
 

  @Output() loggedIn: EventEmitter<boolean> = new EventEmitter();
  @Output() username: EventEmitter<string> = new EventEmitter();

  refreshTokenPayload() {
    let payload = {
    refreshToken: this.getRefreshToken(),
    username: this.getUserName()
  }
  return payload;
  }

  constructor(private http: HttpClient, private localStorage: LocalStorageService) { }

  signup(signupRequestPayload: SignupRequestPayload) {
    return this.http.post('http://localhost:8080/api/auth/signup', signupRequestPayload, { responseType: 'text'});
  }

  login(loginRequestPayload: LoginRequestPayload):Observable<boolean> {
    return this.http.post<LoginResponse>('http://localhost:8080/api/auth/login', loginRequestPayload).pipe(map(data => {
      this.localStorage.store('authenticationToken', data.authenticationToken);
      this.localStorage.store('username', data.username);
      this.localStorage.store('refreshToken', data.refreshToken);
      this.localStorage.store('expiresAt', data.expiresAt);
      return true;
    }));
  }

  getJwtToken() {
    return this.localStorage.retrieve('authenticationToken');
  }

  refreshToken() {
    return this.http.post<LoginResponse>('http://localhost:8080/api/auth/refresh/token',
    this.refreshTokenPayload())
      .pipe(tap(response => {
        this.localStorage.clear('authenticationToken');
        this.localStorage.clear('expiresAt');
        this.localStorage.store('authenticationToken',
          response.authenticationToken);
        this.localStorage.store('expiresAt', response.expiresAt)
      }));
  }

  logout() {
    let myRefreshTokenPayload = {
      refreshToken: this.getRefreshToken(),
      username: this.getUserName()
    }
    this.http.post('http://localhost:8080/api/auth/logout', this.refreshTokenPayload(), {responseType: 'text'})
    .subscribe(data => {
      console.log(data);
    }, error => {
      throwError(error);
    })
    this.localStorage.clear('authenticationToken');
    this.localStorage.clear('username');
    this.localStorage.clear('refreshToken');
    this.localStorage.clear('expiresAt');
  }

  getUserName() {
    return this.localStorage.retrieve('username');
  }
  getRefreshToken() {
    return this.localStorage.retrieve('refreshToken');
  }

  isLoggedIn(): boolean {
    return this.getJwtToken() != null;
  }
}
