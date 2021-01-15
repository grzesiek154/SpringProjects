import { Component, OnInit } from '@angular/core';
import { FormArray, FormControl, FormGroup, Validators } from '@angular/forms';
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
  exercisesData = new FormArray([]);

  // name = new FormControl('');
  // type = new FormControl('');
  // description = new FormControl('');
  // reps? = new FormControl('');
  // duration? = new FormControl('');
  // workout = new FormControl('');

  constructor(private router: Router, private exerciseService: ExerciseService, private workoutService: WorkoutService) {
    this.createExerciseFormGroup = new FormGroup({
          name: new FormControl('', Validators.required),
          type: new FormControl('', Validators.required),
          description: new FormControl('', Validators.required),
          reps: new FormControl(''),
          duration: new FormControl(''),
          workout: new FormControl('')
  });
    this.currentExercise = {
      id: Math.random(),
      name: '',
      type: '',
      description: '',
      reps: 0,
      duration: 0,
      workout: new Workout()
    }
   }

  ngOnInit(): void {
    this.availableWorkouts = this.workoutService.getAll();
    this.availableWorkouts.forEach(workout => {
      console.log("workout: " + workout);
    })
  }

  createExercise() {
    this.currentExercise.name = this.createExerciseFormGroup.get('name').value;
    this.currentExercise.type = this.createExerciseFormGroup.get('type').value;
    this.currentExercise.reps = this.createExerciseFormGroup.get('reps').value;
    this.currentExercise.duration = this.createExerciseFormGroup.get('duration').value;
    this.currentExercise.description = this.createExerciseFormGroup.get('description').value;
    this.currentExercise.workout = this.createExerciseFormGroup.get('workout').value;
    this.exerciseService.createExercise(this.currentExercise);
    this.exerciseService.printAllExercises();
  }

  addNewExerciseForm() {
    const exerciseForm = new FormGroup({
      name: new FormControl('', Validators.required),
      type: new FormControl('', Validators.required),
      description: new FormControl('', Validators.required),
      reps: new FormControl(''),
      duration: new FormControl(''),
      workout: new FormControl('')
    });
    this.exercisesData.push(exerciseForm);
  }

  printAll() {
    this.exerciseService.printAllExercises();
  }

  onSelectWorkout(workout) {
    this.selectedWorkout = workout;
  }
}
