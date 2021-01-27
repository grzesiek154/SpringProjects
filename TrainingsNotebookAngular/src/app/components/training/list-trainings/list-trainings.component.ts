import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Training } from 'src/app/models/Training';
import { TrainingsService } from 'src/app/services/trainings.service';

@Component({
  selector: 'app-list-trainings',
  templateUrl: './list-trainings.component.html',
  styleUrls: ['./list-trainings.component.css']
})
export class ListTrainingsComponent implements OnInit {
  trainings: Training[] = [];

  constructor(private trainingService: TrainingsService, private roter: Router) { }

  ngOnInit(): void {
    this.trainings = this.trainingService.getAll();
  }

  goToCreateTraining() {
    this.roter.navigateByUrl('/create-training');
  }

}
