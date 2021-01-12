import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { TrainingsComponent } from './components/trainings/trainings.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { CreateTrainingComponent } from './components/side-bar/create-training/create-training.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CreateExerciseComponent } from './components/exercise/create-exercise/create-exercise.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    TrainingsComponent,
    SideBarComponent,
    CreateTrainingComponent,
    CreateExerciseComponent
  ],
  imports: [
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
