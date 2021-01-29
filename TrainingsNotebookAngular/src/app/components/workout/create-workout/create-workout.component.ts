import { Component, Input, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
      if(workoutId) {
        this.getWorkout(workoutId);
      }
    })
  
  }
  getWorkout(workoutId: number) {
    let workoutById;
    this.workoutService.getWorkoutById(workoutId).subscribe((workout: Workout) => {
      this.editWorkout(workout);
    });

      console.log("edut workout run")
      this.editWorkout(workoutById);
    
    console.log("workoutById " + workoutId);
  }

  editWorkout(worout: Workout) {
    // this.workoutsForms
    this.currentWorkoutForm.patchValue({
      name: worout.name,
      type: worout.type,
      description: worout.description
    })
    console.log("current form" + this.currentWorkoutForm);
    this.addEditedWorkoutForm(this.currentWorkoutForm);
  }

 
  addEditedWorkoutForm(workoutForm: FormGroup) {
    this.workoutsForms.push(workoutForm);
    //console.log(this.workoutsForms);
    this.workoutsForms.controls.forEach(value => {
      console.log(value)
      console.log(value.value);
      })
 
    // console.log(workoutForm);
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
    this.router.navigateByUrl('/list-workouts');
  }
  
 
}
