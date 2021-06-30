import { NgModule } from '@angular/core';
import { CreateTrainingComponent } from './components/side-bar/create-training/create-training.component';
import { RouterModule, Routes } from '@angular/router';
import { CreateExerciseComponent } from './components/exercise/create-exercise/create-exercise.component';
import { CreateWorkoutComponent } from './components/workout/create-workout/create-workout.component';
import { HomeComponent } from './components/home/home.component';
import { ListWorkoutsComponent } from './components/workout/list-workouts/list-workouts.component';
import { ListExercisesComponent } from './components/exercise/list-exercises/list-exercises.component';
import { ListTrainingsComponent } from './components/training/list-trainings/list-trainings.component';
import { LoginComponent } from './components/auth/login/login.component';
import { SignupComponent } from './components/auth/signup/signup.component';
import { AuthGuard } from './services/auth.guard';
import { MainPageComponent } from './components/main-page/main-page.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { BlogHomeComponent } from './components/blog/blog-home/blog-home.component';
import { CreatePostComponent } from './components/blog/post/create-post/create-post.component';
import { ViewPostComponent } from './components/blog/post/view-post/view-post.component';
import { CalendarMonthComponent } from './components/calendar/calendar-month/calendar-month.component';
import { CalendarMainComponent } from './components/calendar/calendar-main/calendar-main.component';
import { AddTrainingComponent } from './components/calendar/add-training/add-training.component';
import { ListDayTrainingsComponent } from './components/calendar/list-day-trainings/list-day-trainings.component';



const routes: Routes = [
  { path: '', component: MainPageComponent},
  { path: 'home', component: HomeComponent},
  { path: 'user-profile/:name', component: UserProfileComponent,  canActivate: [AuthGuard]},
  { path: 'create-training', component: CreateTrainingComponent, canActivate: [AuthGuard]},
  { path: 'create-exercise', component: CreateExerciseComponent, canActivate: [AuthGuard]},
  { path: 'create-workout', component: CreateWorkoutComponent, canActivate: [AuthGuard]},
  { path: 'edit-workout/:id', component: CreateWorkoutComponent, canActivate: [AuthGuard]},
  { path: 'list-workouts', component: ListWorkoutsComponent, canActivate: [AuthGuard]},
  { path: 'list-exercises', component: ListExercisesComponent, canActivate: [AuthGuard]},
  { path: 'edit-exercise/:id', component: CreateExerciseComponent, canActivate: [AuthGuard]},
  { path: 'list-trainings', component: ListTrainingsComponent, canActivate: [AuthGuard]},
  { path: 'blog', component: BlogHomeComponent, canActivate: [AuthGuard]},
  { path: 'create-post', component: CreatePostComponent, canActivate: [AuthGuard]},
  { path: 'edit-post/:id', component: CreatePostComponent, canActivate: [AuthGuard]},
  { path: 'view-post/:id', component: ViewPostComponent, canActivate: [AuthGuard]},
  { path: 'calendar', component: CalendarMainComponent, canActivate: [AuthGuard]},
  { path: 'add-training', component: AddTrainingComponent, canActivate: [AuthGuard]},
  { path: 'calendar-day-trainings', component: ListDayTrainingsComponent, canActivate: [AuthGuard]},

  { path: 'sign-up', component: SignupComponent },
  { path: 'login', component: LoginComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
