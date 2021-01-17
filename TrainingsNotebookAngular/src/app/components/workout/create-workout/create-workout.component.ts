import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Workout } from 'src/app/models/Workout';
import { WorkoutService } from 'src/app/services/workout.service';

@Component({
  selector: 'app-create-workout',
  templateUrl: './create-workout.component.html',
  styleUrls: ['./create-workout.component.css']
})
export class CreateWorkoutComponent implements OnInit {

  createWorkoutFormGroup: FormGroup;
  currentWorkout: Workout;
  

  constructor(private workoutService: WorkoutService, private fb: FormBuilder) { 

    this.createWorkoutFormGroup = this.fb.group({
      workoutForms: this.fb.array([])
    });

    this.currentWorkout = {
      id: Math.random(),
      name: '',
      type: '',
      description: ''
    }
  }

  ngOnInit(): void {
    let workout1 = new Workout("Przysiady","test workout 1");
    let workout2 = new Workout("Wyciskanie sztanki","test workout 2");
    let workout3 = new Workout("Bieg","test workout 3");
    this.workoutService.createWorkout(workout1);
    this.workoutService.createWorkout(workout2);
    this.workoutService.createWorkout(workout3);

  }

}
