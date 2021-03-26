import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapTestComponent } from './components/map-test/map-test.component';
import { AlertComponent } from './dynamic-components/alert/alert.component';
import { DynamicComponentAppComponent } from './dynamic-components/dynamic-component-app/dynamic-component-app.component';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { Calendar1Component } from './components/calendar1/calendar1.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TemplateFormComponent } from './components/template-form/template-form.component';


@NgModule({
  declarations: [
    AppComponent,
    MapTestComponent,
    AlertComponent,
    DynamicComponentAppComponent,
    Calendar1Component,
    TemplateFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    CalendarModule.forRoot({ provide: DateAdapter, useFactory: adapterFactory }),
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
