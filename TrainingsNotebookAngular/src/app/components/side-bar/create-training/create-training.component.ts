import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { randomInt } from 'crypto';
import { Training } from 'src/app/models/Training';
import { TrainingsService } from 'src/app/services/trainings.service';

@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.css']
})
export class CreateTrainingComponent implements OnInit {
  createTrainingFromGroup: FormGroup;
  currentTraining: Training;
  name = new FormControl('');
  type = new FormControl('');
  date = new FormControl('');

  constructor(private router: Router, private trainingsService: TrainingsService) { 
    this.createTrainingFromGroup = new FormGroup({
      name: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      date: new FormControl('', Validators.required)
    });
    let dateNow = new Date();
    this.currentTraining = {
      name: '',
      type: '',
      createDate: dateNow.getDate()
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
    this.currentTraining.name = this.createTrainingFromGroup.get('name').value;
    this.currentTraining.type = this.createTrainingFromGroup.get('type').value;
    this.currentTraining.createDate = this.createTrainingFromGroup.get('date').value;
    this.trainingsService.createTraining(this.currentTraining);
  }

}
