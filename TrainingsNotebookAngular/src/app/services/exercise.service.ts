import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exercise } from '../models/Exercise';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
}
@Injectable({
  providedIn: 'root'
})
export class ExerciseService {

  exercises: Exercise[] = [];
  private BASE_URL = "http://localhost:8080/api/v1/exercises";

  constructor(private http: HttpClient) {
    let exercise1 = new Exercise();
    exercise1.name = "10 pull ups"
    exercise1.type = "strength exercise";
    exercise1.reps = 10
    this.exercises.push(exercise1);
  }

  postExercise(exercise: Exercise): Observable<Exercise> {
    return this.http.post<Exercise>(this.BASE_URL, exercise, httpOptions);
  }
  updateExercise(exercise): Observable<Exercise> {
    return this.http.post<Exercise>(this.BASE_URL + "/update", exercise, httpOptions);
  }

  getAllExercises(): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.BASE_URL, httpOptions);
  }

  getExerciseById(id: number): Observable<Exercise> {
    return this.http.get<Exercise>(this.BASE_URL + "/" + id);
  }
}
