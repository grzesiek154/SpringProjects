import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Exercise } from 'src/app/models/Exercise';
import { Workout } from 'src/app/models/Workout';
import { ExerciseService } from 'src/app/services/exercise.service';
import { WorkoutService } from 'src/app/services/workout.service';

@Component({
  selector: 'app-create-exercise',
  templateUrl: './create-exercise.component.html',
  styleUrls: ['./create-exercise.component.css']
})
export class CreateExerciseComponent implements OnInit {

  createExerciseFormGroup: FormGroup;
  currentExercise: Exercise;
  selectedWorkout: Workout;
  availableWorkouts: Workout[];

  constructor(private router: Router, private exerciseService: ExerciseService, private workoutService: WorkoutService, private fb: FormBuilder) {
    
    this.createExerciseFormGroup = this.fb.group({
      exercisesForms: this.fb.array([])
    });
   }

  ngOnInit(): void {
    this.availableWorkouts = this.workoutService.getAll();
    this.availableWorkouts.forEach(workout => {
      console.log("workout: " + workout);
    })
  }

  addExerciseForm() {
    this.exercisesForms.push(this.newExerciseForm())
  }

  private newExerciseForm(): FormGroup {
    return this.fb.group({
      name: ['', Validators.required],
      type: ['', Validators.required],
      description: ['', Validators.required],
      reps: 0,
      duration: 0,
      workout: [Workout, Validators.required]
    })
  }

  get exercisesForms(): FormArray {
    return this.createExerciseFormGroup.controls.exercisesForms as FormArray;
  }

  saveExercise(index: number) {
    let exerciseForm = this.exercisesForms.at(index) as FormGroup;
    this.currentExercise = Exercise.mapFormGroupObjectToExercise(exerciseForm);
    this.exerciseService.saveExercise(this.currentExercise);
    this.removeExercise(index);
    this.clearExercise();
  }

  private clearExercise() {
    this.currentExercise = new Exercise();
  }

  removeExercise(index: number) {
    this.exercisesForms.removeAt(index);
  }

  printAll() {
    this.exerciseService.printAllExercises();
  }

}
