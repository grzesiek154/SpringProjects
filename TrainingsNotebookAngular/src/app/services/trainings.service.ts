import { Injectable } from '@angular/core';
import { Exercise } from '../models/Exercise';
import { Training } from '../models/Training';
import { Workout } from '../models/Workout';

@Injectable({
  providedIn: 'root'
})
export class TrainingsService {

  trainings: Training[] = [];
  trainingExercises: Exercise[] = [];
  

  constructor() { 
    let exercise1 = new Exercise();
    exercise1.name = "10 pull ups"
    exercise1.category = "strength exercise";
    exercise1.reps = 10

    let exercise2 = new Exercise();
    exercise2.name = "100 push ups"
    exercise2.category = "strength exercise";
    exercise2.reps = 100
    
    this.trainingExercises.push(exercise1);
    this.trainingExercises.push(exercise2);

    let training1 = new Training()
    training1.name = "cardio training";
    training1.category = "cardio"
    training1.description = "test cardio training";
    training1.exercises = this.trainingExercises;
    this.createTraining(training1);

    this.getAllExercises();
  }

  createTraining(training: Training) {
    this.trainings.push(training);
    console.log("Training " + training.name + " created.")
  }

  // updateTraining(training: Training) {
  //   console.log("Training " + training.name + " created.")
  // }

  deleteTraining(training: Training) {
    console.log("Training " + training.name + " created.");
    const index:number  = this.trainings.indexOf(training);
    this.trainings.splice(index);
  }

  // private getTrainingById(id: number){
  //   const trainingFounded = this.trainings.filter(training =>  training.id = id);
  //   return trainingFounded;
  // }

  private getAllTrainings(){
    this.trainings.forEach(training => {
      console.log("Training: " + training.name);
    })
  }
  getAll() {
    return this.trainings;
  }
  getAllExercises() {
    this.trainingExercises.forEach(exercise => {
      console.log("exercise: " + exercise.name);
    })
  }
}
