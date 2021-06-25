import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { SideBarComponent } from './components/side-bar/side-bar.component';
import { CreateTrainingComponent } from './components/side-bar/create-training/create-training.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CreateExerciseComponent } from './components/exercise/create-exercise/create-exercise.component';
import { CreateWorkoutComponent } from './components/workout/create-workout/create-workout.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { TopMenuComponent } from './components/top-menu/top-menu.component';
import { ListWorkoutsComponent } from './components/workout/list-workouts/list-workouts.component';
import { WorkoutSidebarComponent } from './components/workout/workout-sidebar/workout-sidebar.component';
import { ListTrainingsComponent } from './components/training/list-trainings/list-trainings.component';
import { ListExercisesComponent } from './components/exercise/list-exercises/list-exercises.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TrainingExerciseComponent } from './components/training/training-exercise/training-exercise.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { LoginComponent } from './components/auth/login/login.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { ToastrModule } from 'ngx-toastr';
import { NgxWebstorageModule } from 'ngx-webstorage';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { TokenInterceptor } from './services/token-interceptor';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BlogHomeComponent } from './components/blog/blog-home/blog-home.component';
import { CreatePostComponent } from './components/blog/post/create-post/create-post.component';
import { ViewPostComponent } from './components/blog/post/view-post/view-post.component';
import { EditorModule } from '@tinymce/tinymce-angular';
import { LimitCharatchersPipe } from './pipes/limit-charatchers.pipe';
import { DateTransformPipe } from './pipes/date-transform.pipe';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { CalendarMonthComponent } from './components/calendar/calendar-month/calendar-month.component';
import { CalendarMainComponent } from './components/calendar/calendar-main/calendar-main.component';
import { CalendarDayComponent } from './components/calendar/calendar-day/calendar-day.component';
import { PlaceholderDirective } from './directives/placeholder.directive';
import { AddTrainingComponent } from './components/calendar/add-training/add-training.component';
import { ListDayTrainingsComponent } from './components/calendar/list-day-trainings/list-day-trainings.component';





@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SideBarComponent,
    CreateTrainingComponent,
    CreateExerciseComponent,
    CreateWorkoutComponent,
    TopMenuComponent,
    ListWorkoutsComponent,
    WorkoutSidebarComponent,
    ListTrainingsComponent,
    ListExercisesComponent,
    TrainingExerciseComponent,
    SignupComponent,
    LoginComponent,
    MainPageComponent,
    UserProfileComponent,
    BlogHomeComponent,
    CreatePostComponent,
    ViewPostComponent,
    LimitCharatchersPipe,
    DateTransformPipe,
    CalendarMonthComponent,
    CalendarMainComponent,
    CalendarDayComponent,
    PlaceholderDirective,
    AddTrainingComponent,
    ListDayTrainingsComponent

  ],
  imports: [
    MatSliderModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ToastrModule.forRoot(),
    NgxWebstorageModule.forRoot(),
    NgbModule,
    EditorModule,
    FontAwesomeModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
