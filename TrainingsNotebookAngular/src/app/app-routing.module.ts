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



const routes: Routes = [
  { path: '', component: MainPageComponent},
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'user-profile/:name', component: UserProfileComponent, canActivate: [AuthGuard] },
  { path: 'create-training', component: CreateTrainingComponent, canActivate: [AuthGuard]},
  { path: 'create-exercise', component: CreateExerciseComponent, canActivate: [AuthGuard]},
  { path: 'create-workout', component: CreateWorkoutComponent, canActivate: [AuthGuard]},
  { path: 'edit-workout/:id', component: CreateWorkoutComponent, canActivate: [AuthGuard]},
  { path: 'list-workouts', component: ListWorkoutsComponent, canActivate: [AuthGuard]},
  { path: 'list-exercises', component: ListExercisesComponent, canActivate: [AuthGuard]},
  { path: 'edit-exercise/:id', component: CreateExerciseComponent, canActivate: [AuthGuard]},
  { path: 'list-trainings', component: ListTrainingsComponent, canActivate: [AuthGuard]},

  { path: 'sign-up', component: SignupComponent },
  { path: 'login', component: LoginComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
