import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exercise } from 'src/app/models/Exercise';
import { Training } from 'src/app/models/Training';
import { TrainingsService } from 'src/app/services/trainings.service';

@Component({
  selector: 'app-list-trainings',
  templateUrl: './list-trainings.component.html',
  styleUrls: ['./list-trainings.component.css']
})
export class ListTrainingsComponent implements OnInit {
  trainings: Training[] = [];
  trainingExercises: Exercise[] = [];

  constructor(private trainingService: TrainingsService, private roter: Router) { }

  ngOnInit(): void {
    this.trainingService.getAll().subscribe(trainings => this.trainings = trainings);
    this.trainings.forEach(training => {
      this.trainingExercises = training.exercises;
    })
  }

  goToCreateTraining() {
    this.roter.navigateByUrl('/create-training');
  }

}
