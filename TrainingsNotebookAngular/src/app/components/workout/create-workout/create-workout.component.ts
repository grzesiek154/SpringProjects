import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { Workout } from 'src/app/models/Workout';
import { WorkoutService } from 'src/app/services/workout.service';

@Component({
  selector: 'app-create-workout',
  templateUrl: './create-workout.component.html',
  styleUrls: ['./create-workout.component.css']
})
export class CreateWorkoutComponent implements OnInit{

  subscription: Subscription;
  createWorkoutFormGroup: FormGroup;
  currentWorkout: Workout = new Workout();
  currentWorkoutForm: FormGroup;


  constructor(private workoutService: WorkoutService, private fb: FormBuilder, private router: Router, private activatedRoute: ActivatedRoute) {
    this.createWorkoutFormGroup = this.fb.group({
      workoutsForms: this.fb.array([])
    });
    this.clearWorkout();
    this.currentWorkoutForm = this.newWorkoutForm();
  }

  ngOnInit(): void {
    this.addWorkoutForm();
    this.activatedRoute.paramMap.subscribe(params => {
      const workoutId = +params.get('id'); // + means casting to number
      if (workoutId) {
        this.getWorkout(workoutId);
      }
    })

  }
  getWorkout(workoutId: number) {
    this.workoutService.getWorkoutById(workoutId).subscribe(
      workout => {
        console.log("workout to edit: " + workout.getName);
        console.log("workout to edit id: " + workoutId);
      },
      error => {
        console.error(error);
      }
      // this.editWorkout(workout);
      // console.log("workout to edit: " + workout.getName);
      // console.log("workout to edit id: " + workoutId);
    );
  }

  editWorkout(editedWorkout: Workout) {
    this.currentWorkoutForm.patchValue({
      name: editedWorkout.getName,
      type: editedWorkout.getType,
      description: editedWorkout.getDescription
    })
    console.log("current form" + this.currentWorkoutForm);
    this.addEditedWorkoutForm(this.currentWorkoutForm);
  }

  updateWorkout(index: number){
    let workoutToUpdate = Workout.mapFormGroupObjectToWorkot(this.workoutsForms.at(index) as FormGroup);
    this.workoutService.updateWorkout(workoutToUpdate).subscribe(() => {
      this.router.navigateByUrl('/list-workouts');
    })
    this.removeWorkout(index);
    this.clearWorkout();
  }

  private addEditedWorkoutForm(workoutForm: FormGroup) {
    this.workoutsForms.push(workoutForm);
    this.workoutsForms.controls.forEach(value => {
      console.log(value.value);
    })
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
    this.currentWorkout = Workout.mapFormGroupObjectToWorkot(this.workoutsForms.at(index) as FormGroup);
    this.workoutService.postWorkout(this.currentWorkout).subscribe(() => {
      this.router.navigateByUrl('/list-workouts');
    })
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
    this.router.navigateByUrl('/list-workouts');
  }

}
