import { Component, OnInit } from '@angular/core';
import { Training } from 'src/app/models/Training';
import { CalendarService } from 'src/app/services/calendar.service';

@Component({
  selector: 'app-list-day-trainings',
  templateUrl: './list-day-trainings.component.html',
  styleUrls: ['./list-day-trainings.component.css']
})
export class ListDayTrainingsComponent implements OnInit {

  selectedDate: string;
  dayTrainings: Training[];

  constructor(private calendarService: CalendarService) {
    this.calendarService.selectedDate$.subscribe(date => {
      this.selectedDate = date;
      console.log(date);
    })
   }

  ngOnInit(): void {
    this.calendarService.getTrainingsInDay(this.selectedDate).subscribe(trainings => {
      this.dayTrainings = trainings;
      trainings.forEach(training => console.log(training));
    })
  }

}
