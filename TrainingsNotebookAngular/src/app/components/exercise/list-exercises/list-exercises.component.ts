import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Exercise } from 'src/app/models/Exercise';
import { ExerciseService } from 'src/app/services/exercise.service';

@Component({
  selector: 'app-list-exercises',
  templateUrl: './list-exercises.component.html',
  styleUrls: ['./list-exercises.component.css']
})
export class ListExercisesComponent implements OnInit {
  exercises: Exercise[] = [];

  constructor(private exerciseService: ExerciseService, private router: Router) { }

  ngOnInit(): void {
    this.exerciseService.getAllExercises().subscribe(
      data => {
         this.exercises = data
      });
  }

  goToCreateExercise() {
    this.router.navigateByUrl('/create-exercise');
  }

  editButton(exerciseId: number) {
    this.router.navigate(['/edit-exercise',exerciseId]);
  }
}
