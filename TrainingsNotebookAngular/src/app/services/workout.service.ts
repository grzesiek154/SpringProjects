import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Workout } from '../models/Workout';
import { Observable } from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
}

@Injectable({
  providedIn: 'root'
})

export class WorkoutService {
  workouts: Workout[] = [];
  private BASE_URL = "http://localhost:3000/workouts";


  constructor(private http: HttpClient) {

  }

  postWorkout(workout): Observable<Workout> {
    return this.http.post<Workout>(this.BASE_URL, workout, httpOptions);
  }
  putWorkout(workout): Observable<Workout> {
    return this.http.put<Workout>(this.BASE_URL, workout);
  }
  printAllWorkouts() {
    this.workouts.forEach(workout => {
      console.log(workout);
    })
  }

  getAll(): Observable<Workout[]> {
    return this.http.get<Workout[]>(this.BASE_URL);
  }

  getWorkoutById(id: number): Observable<Workout> {
    return this.http.get<Workout>(this.BASE_URL + "/" + id);
  }
}
