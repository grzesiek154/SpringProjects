import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CalendarDay } from 'src/app/models/CalendarDay';
import { Training } from 'src/app/models/Training';
import { CalendarService } from 'src/app/services/calendar.service';
import { TrainingsService } from 'src/app/services/trainings.service';

@Component({
  selector: 'app-add-training',
  templateUrl: './add-training.component.html',
  styleUrls: ['./add-training.component.css']
})
export class AddTrainingComponent implements OnInit {
  addTrainingFormGroup: FormGroup;
  trainingsInDay: Training[] = [];
  availableTrainings: Training[] = [];
  month: number;
  year: number;
  day: number;
  selectedDate: string;
  isValid: boolean = false;

  constructor(private calendarService: CalendarService, private activatedRoute: ActivatedRoute, private fb: FormBuilder, private trainingsService: TrainingsService, private router: Router) { 
    this.addTrainingFormGroup = this.fb.group({
      selectedTrainings: this.fb.array([
        this.fb.control('', [Validators.required])
      ], Validators.required)
    });
  }

  ngOnInit(): void {
    this.calendarService.currentMonth$.subscribe(currentMont => {
      this.month = currentMont;
    })
    this.calendarService.currentYear$.subscribe(currentYear => {
      this.year = currentYear;
    })

    this.calendarService.currentDay$.subscribe(currentDay => {
      this.day = currentDay;
    })

    this.trainingsService.getAll().subscribe(trainings => {
      this.availableTrainings = trainings;
    })

    this.calendarService.selectedDate$.subscribe(date => {
      console.log("selected date: " + date);
      this.selectedDate = date;
    })
  }

  saveCalendarDay() {
    let calendarDay = new CalendarDay();
    calendarDay.date = this.selectedDate;
    calendarDay.trainings = this.trainingsInDay;
    this.calendarService.postCalendarDay(calendarDay).subscribe(() => {
      this.backToCalendarMonth();
    })
  }

  saveTrainingInCalendar(index) {
    let trainingFormControl = this.selectedTrainings.at(index) as FormControl;
    this.trainingsInDay.push(trainingFormControl.value);
    this.isValid = true;
    trainingFormControl.disable({ onlySelf: true });
  }

  addTraining() {
    this.selectedTrainings.push(this.fb.control('',[Validators.required]))
  }

  removeExerciseFormControl(index: number) {
    this.selectedTrainings.removeAt(index);
  }
  
  get selectedTrainings(): FormArray {
    return this.addTrainingFormGroup.get('selectedTrainings') as FormArray;
  }

  backToCalendarMonth() {
    this.router.navigateByUrl('/calendar');
  }
}
