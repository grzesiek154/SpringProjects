import { Component } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppService } from './app.service';
import { Router } from '@angular/router';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Demo';
  data = {};
  greeting = {};
  constructor(private http: HttpClient, private appService: AppService, private router: Router) {
    this.appService.authenticate(undefined, undefined);
  }

  logout() {
    this.http.post('logout', {}).pipe(
     finalize(() => {
        this.appService.authenticated = false;
        this.router.navigateByUrl('/login');
     })
    ).subscribe;
  }

  }

