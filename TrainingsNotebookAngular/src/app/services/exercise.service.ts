import { Injectable } from '@angular/core';
import { Exercise } from '../models/Exercise';

@Injectable({
  providedIn: 'root'
})
export class ExerciseService {
  exercises: Exercise[];

  constructor() { }

  getAll() {
    return this.exercises;
  }

  createExercise(exercise) {
    this.exercises.push(exercise);
  }
  printAllExercises(){
    this.exercises.forEach(exercise => {
      console.log(exercise);
    })
  }
}
