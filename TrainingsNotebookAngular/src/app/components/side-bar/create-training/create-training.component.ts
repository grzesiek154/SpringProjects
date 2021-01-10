import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Training } from 'src/app/models/Training';
import { TrainingsService } from 'src/app/services/trainings.service';

@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.css']
})
export class CreateTrainingComponent implements OnInit {
  createTrainingFormGroup: FormGroup;
  currentTraining: Training;
  name = new FormControl('');
  type = new FormControl('');
  date = new FormControl('');

  constructor(private router: Router, private trainingsService: TrainingsService) { 
    this.createTrainingFormGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      date: new FormControl('', Validators.required),
      description: new FormControl('')
    });
    let dateNow = new Date();
    this.currentTraining = {
      id: Math.random(),
      name: '',
      type: '',
      createDate: dateNow.getDate(),
      description: ''
    }
  }

  ngOnInit(): void {
    let training1: Training = new Training();
    training1.name = "Push ups";
    let training2: Training = new Training();
    training2.name = "Pull ups";
    this.trainingsService.createTraining(training1);
    this.trainingsService.createTraining(training2);
  }

  createTraining() {
    this.currentTraining.name = this.createTrainingFormGroup.get('name').value;
    this.currentTraining.type = this.createTrainingFormGroup.get('type').value;
    this.currentTraining.createDate = this.createTrainingFormGroup.get('date').value;
    this.currentTraining.description = this.createTrainingFormGroup.get('description').value;
    this.trainingsService.createTraining(this.currentTraining);
  }

}
