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



const routes: Routes = [
  { path: '', component: MainPageComponent},
  { path: 'home', component: HomeComponent, },
  { path: 'user-profile/:name', component: UserProfileComponent,  },
  { path: 'create-training', component: CreateTrainingComponent},
  { path: 'create-exercise', component: CreateExerciseComponent},
  { path: 'create-workout', component: CreateWorkoutComponent, },
  { path: 'edit-workout/:id', component: CreateWorkoutComponent, },
  { path: 'list-workouts', component: ListWorkoutsComponent, },
  { path: 'list-exercises', component: ListExercisesComponent},
  { path: 'edit-exercise/:id', component: CreateExerciseComponent},
  { path: 'list-trainings', component: ListTrainingsComponent},
  { path: 'blog', component: BlogHomeComponent},
  { path: 'create-post', component: CreatePostComponent},
  { path: 'edit-post/:id', component: CreatePostComponent},
  { path: 'view-post/:id', component: ViewPostComponent},
  { path: 'calendar', component: CalendarMainComponent},
  { path: 'add-training', component: AddTrainingComponent},

  { path: 'sign-up', component: SignupComponent },
  { path: 'login', component: LoginComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
