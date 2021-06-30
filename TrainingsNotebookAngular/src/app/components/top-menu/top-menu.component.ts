import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {
  isLoggedIn: boolean;
  username: string;


  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.loggedIn.subscribe((data: boolean) => this.isLoggedIn = data);
    this.authService.username.subscribe((data: string) => this.username = data);
    this.isLoggedIn = this.authService.isLoggedIn();
    this.username = this.authService.getUserName();

    console.log("isLoggedIn: " + this.isLoggedIn)
  }

  // goToListTrainings() {
  //   this.router.navigateByUrl('/list-trainings');
  // }

  // goToListExercises() {
  //   this.router.navigateByUrl('/list-exercises');
  // }

  // goToListWorkouts() {
  //   this.router.navigateByUrl('/list-workouts');
  // }

  goToUserProfile() {
    this.router.navigateByUrl('/user-profile/' + this.username);
  }

  logout() {
    this.authService.logout();
    this.isLoggedIn = false;
    this.router.navigateByUrl('')
  }
}
