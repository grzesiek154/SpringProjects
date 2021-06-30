import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { Training } from 'src/app/models/Training';
import { CalendarService } from 'src/app/services/calendar.service';

@Component({
  selector: 'app-calendar-day',
  templateUrl: './calendar-day.component.html',
  styleUrls: ['./calendar-day.component.css']
})
export class CalendarDayComponent implements OnInit {

  @Input() day: number;
  @Input() month: number;
  @Input() year: number;
  trainings: Training[];
  isDisplayButton: boolean = false;

  constructor(private router: Router, private calendarServive: CalendarService) { 

  }

  ngOnInit(): void {
    this.getTrainingsInDay();
  }


  goToCreateTraining() {
    this.calendarServive.announceSelectedDate(new Date(this.year, this.month, this.day).toISOString().substring(0,10));
    this.router.navigateByUrl('/add-training');
  }

  goToCalendarDayTrainings() {
    let date: string = new Date(this.year, this.month, this.day).toISOString().substring(0,10);
    this.calendarServive.announceSelectedDate(date);
    this.router.navigateByUrl('calendar-day-trainings');
  }

  getTrainingsInDay() {
    let date: string = new Date(this.year, this.month, this.day).toISOString().substring(0,10);
    this.calendarServive.getTrainingsInDay(date).pipe(filter(data => data.length > 0)).
    subscribe(trainings => {
        this.isDisplayButton = true;
    })
  }
}
