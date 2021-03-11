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
  getExercisesByCategory(category: string): Observable<Exercise[]> {
    return this.http.get<Exercise[]>(this.BASE_URL+"/category/"+ category, httpOptions);
  }
}
