import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { CalendarDay } from '../models/CalendarDay';
import { Training } from '../models/Training';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*'
  })
}

@Injectable({
  providedIn: 'root'
})
export class CalendarService {

  private BASE_URL = "http://localhost:8080/api/v1/calendarDays";
  private currentYear = new Date().getFullYear();
  private currentMonth = new Date().getMonth();
  private selectedDay = new Date().getDay();
  private selectedDate = "";
  private setCurrentYear: BehaviorSubject<number> = new BehaviorSubject(this.currentYear);
  private setCurrentMonth: BehaviorSubject<number> = new BehaviorSubject(this.currentMonth);
  private setSelectedDay: BehaviorSubject<number> = new BehaviorSubject(this.selectedDay);
  private setSelectedDate: BehaviorSubject<string> = new BehaviorSubject(this.selectedDate);

  currentYear$ = this.setCurrentYear.asObservable();
  currentMonth$ = this.setCurrentMonth.asObservable();
  currentDay$ = this.setSelectedDay.asObservable();
  selectedDate$ = this.setSelectedDate.asObservable();

    constructor(private http: HttpClient) {}

    announceYear(year: number) {
      this.setCurrentYear.next(year);
    }

    announceMonth(month: number) {
      this.setCurrentMonth.next(month);
    }

    announeSelectedDay(day: number) {
      this.setSelectedDay.next(day);
    }

    announceSelectedDate(date: string) {
      this.setSelectedDate.next(date);
    }

    postCalendarDay(calendarDay: CalendarDay): Observable<CalendarDay> {
      return this.http.post<CalendarDay>(this.BASE_URL, calendarDay, httpOptions);
    }

    getNumberOfTrainingsInDay(date: string): Observable<number> {
      return this.http.get<number>(this.BASE_URL + "/numberOfTrainingsInDay/" + date, httpOptions)
    }

    getTrainingsInDay(date: string): Observable<Training[]> {
      return this.http.get<Training[]>(this.BASE_URL + "/trainingsInDay/" + date, httpOptions)
    }
}
