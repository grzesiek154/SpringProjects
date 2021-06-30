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
export class CreateWorkoutComponent implements OnInit {

  subscription: Subscription;
  createWorkoutFormGroup: FormGroup;
  currentWorkout: Workout = new Workout();
  currentWorkoutForm: FormGroup;
  updateId: number;
  isEdited: boolean = false;


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
        this.isEdited = true;
        this.getWorkout(workoutId);
      }
    })

  }
  getWorkout(workoutId: number) {
    this.workoutService.getWorkoutById(workoutId).subscribe(
      workout => {
        this.updateId = workout.id;
        this.editWorkout(workout);
      },
      error => {
        console.error(error);
      }
    );
  }

  private editWorkout(editedWorkout: Workout) {
    this.workoutsForms.removeAt(0);
    this.currentWorkoutForm.patchValue({
      name: editedWorkout.name,
      description: editedWorkout.description
    })
    this.addEditedWorkoutForm(this.currentWorkoutForm);
  }

  updateWorkout(index: number) {
    const workoutToUpdate = Workout.mapFormGroupObjectToWorkot(this.workoutsForms.at(index) as FormGroup);
    workoutToUpdate.id = this.updateId;
    this.workoutService.updateWorkout(workoutToUpdate).subscribe(() => {
      this.router.navigateByUrl('/list-workouts');
    })
  }

  private addEditedWorkoutForm(workoutForm: FormGroup) {
    this.workoutsForms.push(workoutForm);
  }

  addWorkoutForm() {
    this.workoutsForms.push(this.newWorkoutForm());
  }

  private newWorkoutForm(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
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
