import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-top-menu',
  templateUrl: './top-menu.component.html',
  styleUrls: ['./top-menu.component.css']
})
export class TopMenuComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  goToCreateTraining() {
    this.router.navigateByUrl('/create-training');
  }

  goToCreateExercise() {
    this.router.navigateByUrl('/create-exercise');
  }

  goToCreateWorkout() {
    this.router.navigateByUrl('/create-workout');
  }
}
