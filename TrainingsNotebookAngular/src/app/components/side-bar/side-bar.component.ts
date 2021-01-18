import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

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
