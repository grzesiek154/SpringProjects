import { ComponentFactory } from '@angular/core';
import { ComponentRef } from '@angular/core';
import { Component, ComponentFactoryResolver, Input, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { Subscription } from 'rxjs';
import { PlaceholderDirective } from 'src/app/directives/placeholder.directive';
import { CalendarService } from 'src/app/services/calendar.service';
import { CalendarDayComponent } from '../calendar-day/calendar-day.component';

@Component({
  selector: 'app-calendar-month',
  templateUrl: './calendar-month.component.html',
  styleUrls: ['./calendar-month.component.css']
})
export class CalendarMonthComponent implements OnInit {

  currentMonth: number;
  currentYear: number;
  subscription: Subscription = new Subscription();
  @ViewChild(PlaceholderDirective, {static: true}) calendarDayHost: PlaceholderDirective;
  componentRef: ComponentRef<any>;

  constructor(private calendarService: CalendarService, private resolver: ComponentFactoryResolver) {

   }

  ngOnInit(): void {
    this.calendarService.currentYear$.subscribe(year => {
      this.currentYear = year;
      //this.getDaysInMonth(year);
      this.createDaysInMonth(year);
   
    });
    this.calendarService.currentMonth$.subscribe(month => {
      this.currentMonth = month;
      //this.getDaysInMonth(month);
      this.createDaysInMonth(month + 1);
    });
  
  }
  
  ngOnDestroy() {
    // prevent memory leak when component destroyed
    this.subscription.unsubscribe();
  }

  createDaysInMonth(month) {
    let daysInMonth = this.getDaysInMonth(month);
    const hostViewContainerRef = this.calendarDayHost.viewContainerRef;
    hostViewContainerRef.clear();

    for(let i = 1; i <= daysInMonth; i++) {
    const factory: ComponentFactory<any> = this.resolver.resolveComponentFactory(CalendarDayComponent);
    this.componentRef = hostViewContainerRef.createComponent(factory);
    this.componentRef.instance.day = i;
    this.componentRef.instance.month = this.currentMonth; 
    this.componentRef.instance.year = this.currentYear;  
    } 
  }

  private getDaysInMonth(month) {
    let daysInMont = new Date(this.currentYear, month,0).getDate();
    return daysInMont;
  }
 
}
