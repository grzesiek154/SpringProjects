import { Injectable } from '@angular/core';
import { Exercise } from '../models/Exercise';
import { Training } from '../models/Training';

@Injectable({
  providedIn: 'root'
})
export class TrainingsService {

  trainings: Training[];
  

  constructor() { }

  createTraining(training: Training) {
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

  private getTrainingById(id: number){
    const trainingFounded = this.trainings.filter(training =>  training.id = id);
    return trainingFounded;
  }

  private getAllTrainings(){
    this.trainings.forEach(training => {
      console.log("Training: " + training.name);
    })
  }
}
