import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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
  currentExerciseForm: FormGroup;
  currentExercise: Exercise = new Exercise();
  availableWorkouts: Workout[];
  updateId: number;

  constructor(private router: Router, private activatedRoute: ActivatedRoute, private exerciseService: ExerciseService, private workoutService: WorkoutService, private fb: FormBuilder) {
    
    this.createExerciseFormGroup = this.fb.group({
      exercisesForms: this.fb.array([])
    });
   }

  ngOnInit(): void {
    this.addExerciseForm();
    this.currentExerciseForm = this.newExerciseForm();
    this.workoutService.getAll().subscribe(data=>{
      this.availableWorkouts = data;
    });
    this.activatedRoute.paramMap.subscribe(params => {
      const exerciseId = +params.get('id'); // + means casting to number
      if (exerciseId) {
        this.getExercise(exerciseId);
      }
    })
  }

  saveExercise(index: number) {
    let exerciseForm = this.exercisesForms.at(index) as FormGroup;
    this.currentExercise = Exercise.mapFormGroupObjectToExercise(exerciseForm);
    this.exerciseService.postExercise(this.currentExercise).subscribe(() => {
      this.router.navigateByUrl("/list-exercises");
    })
    this.removeExercise(index);
    this.clearExercise();
  }

  updateExercise(index: number) {
    const exerciseToUpdate = Exercise.mapFormGroupObjectToExercise(this.exercisesForms.at(index) as FormGroup);
    exerciseToUpdate.id = this.updateId;
    this.exerciseService.updateExercise(exerciseToUpdate).subscribe(() => {
      this.router.navigateByUrl('/list-exercises');
    })
  }

  getExercise(id: number) {
    this.exerciseService.getExerciseById(id).subscribe(
      exercise => {
        this.updateId = exercise.id;
        this.editExercise(exercise);
      },
      error => {
        console.error(error);
      }
    )
  }

  editExercise(editedExercise: Exercise) {
   
    this.currentExerciseForm.patchValue({
      name: editedExercise.name,
      type: editedExercise.type,
      reps: editedExercise.reps,
      duration: editedExercise.duration,
      description: editedExercise.description,
      workout_id: editedExercise.workout
      
    })
    console.log("workout: " + this.currentExercise.workout);
    this.addEditedExerciseForm(this.currentExerciseForm);
   // this.removeExercise(0);
  }

  private addEditedExerciseForm(exerciseForm: FormGroup) {
    this.exercisesForms.push(exerciseForm);
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


  private clearExercise() {
    this.currentExercise = new Exercise();
  }

  removeExercise(index: number) {
    this.exercisesForms.removeAt(index);
  }

  printAll() {
    this.router.navigateByUrl('/list-exercises');
  }

}
