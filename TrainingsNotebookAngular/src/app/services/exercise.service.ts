import { Injectable } from '@angular/core';
import { Exercise } from '../models/Exercise';
import { Workout } from '../models/Workout';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  exercises: Exercise[] = [];
  

  constructor() {
    let exercise1 = new Exercise();
    exercise1.name = "10 pull ups"
    exercise1.type = "strength exercise";
    exercise1.reps = 10
    let exerciseWorkout = new Workout();
    exercise1.name = "Exercise Workout";
    exercise1.workout = exerciseWorkout;
    this.exercises.push(exercise1);
   }

  getAll() {
    return this.exercises;
  }

  saveExercise(exercise) {
    this.exercises.push(exercise);
  }
  printAllExercises(){
    this.exercises.forEach(exercise => {
      console.log(exercise);
    })
  }
}
