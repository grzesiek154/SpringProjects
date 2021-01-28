import { Component, OnInit } from '@angular/core';
import { WorkoutService } from 'src/app/services/workout.service';

@Component({
  selector: 'app-edit-workout',
  templateUrl: './edit-workout.component.html',
  styleUrls: ['./edit-workout.component.css']
})
export class EditWorkoutComponent implements OnInit {
  workoutId: number;

  constructor(private workoutService: WorkoutService) { }

  ngOnInit(): void {
  }



}
