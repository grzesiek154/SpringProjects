import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Exercise } from 'src/app/models/Exercise';
import { Training } from 'src/app/models/Training';
import { ExerciseService } from 'src/app/services/exercise.service';
import { TrainingsService } from 'src/app/services/trainings.service';

@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.css']
})
export class CreateTrainingComponent implements OnInit {
  createTrainingFormGroup: FormGroup;
  currentTraining: Training;
  trainingExercises: Exercise[];
  availableExercises: Exercise[] = [];
  exerciseCategories = ["ABS", "BACK", "CARDIO", "CHEST", "LEGS", "SHOULDERS", "STRETCHING"];


  constructor(private router: Router, private trainingsService: TrainingsService, private fb: FormBuilder, private exerciseService: ExerciseService) {
    this.createTrainingFormGroup = this.fb.group({
      name: ['', Validators.required],
      category: ['', Validators.required],
      description: ['', Validators.required],
      exercisesFormArray: this.fb.array([
        this.fb.group({
          exercise: ['', Validators.required],
          category: ['', Validators.required] 
        })
        // this.fb.control(''),
        // this.fb.control('')
      ])
    });
    this.clearTraining();

  }

  ngOnInit(): void {
    this.onChanges();
  }

  onChanges(): void {
    this.createTrainingFormGroup.get('exercisesFormArray').valueChanges.subscribe(exerciseForm => {
      //this.updateAvailableWorkouts(category);
      const exercise = exerciseForm as FormGroup;
      exerciseForm.forEach(element => {
        console.log(element.category);
      });
    })
  }

  updateAvailableWorkouts(value) {
    this.exerciseService.getExercisesByCategory(value).subscribe(exercisesByCategory => {
      this.availableExercises = exercisesByCategory;
    });
  }
  saveTraining() {
    this.currentTraining = Training.mapFormGroupObjectToTraining(this.createTrainingFormGroup as FormGroup);
    console.log(this.createTrainingFormGroup.value);
    this.trainingsService.createTraining(this.currentTraining);
    this.backToMainPage();
  }

  clearTraining() {
    this.currentTraining = new Training();
  }

  backToMainPage() {
    this.router.navigateByUrl("/");
  }

  addExercise() {
    const exerciseAndCategoryForm = this.fb.group({
      exercise: ['', Validators.required],
      category: ['', Validators.required] 
    });
    this.exercisesFormArray.push(exerciseAndCategoryForm);
  }
  get exercisesFormArray() {
    return this.createTrainingFormGroup.get('exercisesFormArray') as FormArray;
  }
  printAll() {
    this.router.navigateByUrl('/list-trainings');
  }
}
