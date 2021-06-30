import { Component, OnInit } from '@angular/core';
import { CalendarService } from 'src/app/services/calendar.service';

@Component({
  selector: 'app-calendar-main',
  templateUrl: './calendar-main.component.html',
  styleUrls: ['./calendar-main.component.css']
})
export class CalendarMainComponent implements OnInit {

  currentYear: number;
  currentMonth: number;

  constructor(private calendarService: CalendarService) { 
    calendarService.currentYear$.subscribe(year => {
      this.currentYear = year;
    });
    calendarService.currentMonth$.subscribe(month => {
      this.currentMonth = month;
    });
  }

  ngOnInit(): void {
  }

  nextYear(year) {
    this.calendarService.announceYear(year);
  }

  nextMonth() {
    let newMonth = this.currentMonth + 1;
    this.calendarService.announceMonth(newMonth);
    console.log(this.currentMonth);
  }
  previousMonth() {
    let newMonth = this.currentMonth - 1;
    this.calendarService.announceMonth(newMonth);
    console.log(this.currentMonth);
  }
}
