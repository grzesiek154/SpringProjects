import { Injectable } from '@angular/core';
import { Workout } from '../models/Workout';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  workouts: Workout[] = [];

  constructor() {
    let workout1 = new Workout();
    workout1.id = 1;
    workout1.name = "Przysiady";
    workout1.type = "test workout 1";
    let workout2 = new Workout();
    workout2.id = 2;
    workout2.name = "Wyciskanie sztanki";
    workout2.description = "test workout 2";
    this.saveWorkout(workout1);
    this.saveWorkout(workout2);
   }

  saveWorkout(workout) {
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

   getWorkoutById(id: number){
    const workoutFounded = this.workouts.filter(workout =>  workout.id = id);
    return workoutFounded;
  }
}
