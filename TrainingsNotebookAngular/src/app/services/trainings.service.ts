import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Exercise } from '../models/Exercise';
import { Training } from '../models/Training';
import { Workout } from '../models/Workout';


const httpOptions = {
  headers: new HttpHeaders({ 
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin':  '*'
})
}

@Injectable({
  providedIn: 'root'
})
export class TrainingsService {

  trainings: Training[] = [];
  trainingExercises: Exercise[] = [];
  private BASE_URL = "http://localhost:8080/api/v1/trainings";
  
  
  constructor(private http: HttpClient) {
    this.getAllExercises();
  }

  addTraining(training: Training):Observable<Training> {
    return this.http.post<Training>(this.BASE_URL,training, httpOptions);
  }
  getAll(): Observable<Training[]> {
    return this.http.get<Training[]>(this.BASE_URL, httpOptions);
  }

  getTrainingById(id: number): Observable<Training> {
    return this.http.get<Training>(this.BASE_URL + "/" + id, httpOptions);
  }

  getTrainingsByCategory(category: string): Observable<Training[]> {
    return this.http.get<Training[]>(this.BASE_URL + "/category/" + category, httpOptions);
  }

  getTrainingsByCalendarDay(calendarDayId: number): Observable<Training[]> {
    return this.http.get<Training[]>(this.BASE_URL + "calendarDay/" + calendarDayId, httpOptions);
  }

  deleteTraining(trainingId: number):Observable<void> {
    return this.http.delete<void>(this.BASE_URL + "/" + trainingId, httpOptions);
  }

  getAllExercises() {
    this.trainingExercises.forEach(exercise => {
      console.log("exercise: " + exercise.name);
    })
  }
}
