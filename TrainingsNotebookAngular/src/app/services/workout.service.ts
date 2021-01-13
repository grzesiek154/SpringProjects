import { Injectable } from '@angular/core';
import { Workout } from '../models/Workout';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  workouts: Workout[] = [];

  constructor() {

   }

  createWorkout(workout) {
    this.workouts.push(workout);
  }
  printAllWorkouts(){
    this.workouts.forEach(workout => {
      console.log(workout);
    })
  }

  getAll() {
    return this.workouts;
  }
}
