import { Component, OnInit, Output,EventEmitter, Input} from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ExerciseService } from 'src/app/services/exercise.service';
import { TrainingsService } from 'src/app/services/trainings.service';


@Component({
  selector: 'app-training-exercise',
  templateUrl: './training-exercise.component.html',
  styleUrls: ['./training-exercise.component.css']
})
export class TrainingExerciseComponent implements OnInit {
  exerciseForm: FormGroup;
  exercisesByCategory = [];
  exerciseCategories = ["ABS", "BACK", "CARDIO", "CHEST", "LEGS", "SHOULDERS", "STRETCHING"];
  category: string = this.exerciseCategories[0];
  @Output() newExerciseFormEvetnt = new EventEmitter();

  constructor(private fb: FormBuilder, private exerciseService: ExerciseService, private trainingsService: TrainingsService) {

    this.exerciseForm = this.fb.group({
      exercise: ['', Validators.required],
      category: ['', Validators.required] 
    })

  }
  ngOnInit(): void {
  
  }

  saveExerciseToTraining() {
    //this.newExerciseFormEvetnt.emit(this.exerciseForm.get('exercise'));
    this.trainingsService.announceNewExercise(this.exerciseForm.get('exercise') as FormControl);
    this.exerciseForm.get('exercise').disable({ onlySelf: true });
    this.exerciseForm.get('category').disable({ onlySelf: true });
  }
  updateCategory(e) {
    let categoryEvent: string = e.target.value;
    categoryEvent = categoryEvent.slice(3, categoryEvent.length);
    this.getExercisesByCategory(categoryEvent);
  }

  private getExercisesByCategory(category) {
    console.log("categoryEvent: " + category);
    this.exerciseService.getExercisesByCategory(category).subscribe(exercises => {
      this.exercisesByCategory = exercises;
    })
  }



}
