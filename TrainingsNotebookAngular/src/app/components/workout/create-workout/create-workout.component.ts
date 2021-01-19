import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Workout } from 'src/app/models/Workout';
import { WorkoutService } from 'src/app/services/workout.service';

@Component({
  selector: 'app-create-workout',
  templateUrl: './create-workout.component.html',
  styleUrls: ['./create-workout.component.css']
})
export class CreateWorkoutComponent implements OnInit {

  createWorkoutFormGroup: FormGroup;
  currentWorkout: Workout = new Workout();
  

  constructor(private workoutService: WorkoutService, private fb: FormBuilder) { 

    this.createWorkoutFormGroup = this.fb.group({
      workoutsForms: this.fb.array([])
    });

    this.clearWorkout();
  }

  ngOnInit(): void {
    let workout1 = new Workout();
    workout1.name = "Przysiady";
    workout1.type = "test workout 1";
    let workout2 = new Workout();
    workout2.name = "Wyciskanie sztanki";
    workout2.description = "test workout 2";
    this.workoutService.saveWorkout(workout1);
    this.workoutService.saveWorkout(workout2);
  

  }
  addWorkoutForm() {
    this.workoutsForms.push(this.newWorkoutForm());
  }
  private newWorkoutForm(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      description: ''
    });
  }

  get workoutsForms(): FormArray {
    return this.createWorkoutFormGroup.controls.workoutsForms as FormArray;
  }

  saveWorkout(index: number) {
    console.log("index: " + index);
    this.currentWorkout = Workout.mapFormGroupObjectToWorkot(this.workoutsForms.at(index) as FormGroup);
    this.workoutService.saveWorkout(this.currentWorkout);
    this.removeWorkout(index);
    this.clearWorkout();
  }

  private clearWorkout() {
    this.currentWorkout = new Workout();
  }

  removeWorkout(index: number) {
    this.workoutsForms.removeAt(index);
  }
  printAll() {
    this.workoutService.printAllWorkouts();
  }

}
