import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { TrainingsComponent } from './components/trainings/trainings.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { CreateTrainingComponent } from './components/side-bar/create-training/create-training.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TrainingsComponent,
    SideBarComponent,
    CreateTrainingComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
