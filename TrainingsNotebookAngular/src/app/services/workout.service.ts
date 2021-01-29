import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Workout } from '../models/Workout';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WorkoutService {
  workouts: Workout[] = [];
  private BASE_URL = "http://localhost:3000/workouts";

  constructor(private http: HttpClient) {
    // let workout1 = new Workout();
    // workout1.id = 1;
    // workout1.name = "Przysiady";
    // workout1.type = "test workout 1";
    // let workout2 = new Workout();
    // workout2.id = 2;
    // workout2.name = "Wyciskanie sztanki";
    // workout2.description = "test workout 2";
    // this.saveWorkout(workout1);
    // this.saveWorkout(workout2);
   }

  saveWorkout(workout) {
    this.workouts.push(workout);
  }
  printAllWorkouts(){
    this.workouts.forEach(workout => {
      console.log(workout);
    })
  }

  getAll(): Observable<Workout[]> {
    return this.http.get<Workout[]>(this.BASE_URL);
  }

   getWorkoutById(id: number): Observable<Workout> {
    return this.http.get<Workout>(this.BASE_URL + "/" +id);
  }
}
