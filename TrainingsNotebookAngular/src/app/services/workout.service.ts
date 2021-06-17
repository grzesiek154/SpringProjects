import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Workout } from '../models/Workout';
import { Observable } from 'rxjs';

const httpOptions = {
  headers: new HttpHeaders({ 
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin':  '*'
})
}

@Injectable({
  providedIn: 'root'
})

export class WorkoutService {
  
  workouts: Workout[] = [];
  private BASE_URL = "http://localhost:8080/api/v1/workouts";

  constructor(private http: HttpClient) {
  }
  
  postWorkout(workout: Workout): Observable<Workout> {
    return this.http.post<Workout>(this.BASE_URL, workout, httpOptions);
  }
  updateWorkout(workout: Workout): Observable<Workout> {
    return this.http.post<Workout>(this.BASE_URL + "/update", workout, httpOptions);
  }

  getAll(): Observable<Workout[]> {
    return this.http.get<Workout[]>(this.BASE_URL, httpOptions);
  }

  getWorkoutById(id: number): Observable<Workout> { 
    return this.http.get<Workout>(this.BASE_URL + "/" + id);
  }
}
