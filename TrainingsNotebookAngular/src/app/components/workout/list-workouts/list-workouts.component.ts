import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Workout } from 'src/app/models/Workout';
import { WorkoutService } from 'src/app/services/workout.service';

@Component({
  selector: 'app-list-workouts',
  templateUrl: './list-workouts.component.html',
  styleUrls: ['./list-workouts.component.css']
})
export class ListWorkoutsComponent implements OnInit {

  workouts: Workout[] = [];

  constructor(private workoutService: WorkoutService, private router: Router) { }

  ngOnInit(): void {
    this.workouts = this.workoutService.getAll();
  }

  goToCreateWorkout() {
    this.router.navigateByUrl('/create-workout');
  }

}
