import { NgModule } from '@angular/core';
import { CreateTrainingComponent } from './components/side-bar/create-training/create-training.component';
import { RouterModule, Routes } from '@angular/router';
import { CreateExerciseComponent } from './components/exercise/create-exercise/create-exercise.component';
import { CreateWorkoutComponent } from './components/workout/create-workout/create-workout.component';
import { HomeComponent } from './components/home/home.component';
import { ListWorkoutsComponent } from './components/workout/list-workouts/list-workouts.component';
import { ListExercisesComponent } from './components/exercise/list-exercises/list-exercises.component';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'create-training', component: CreateTrainingComponent },
  { path: 'create-exercise', component: CreateExerciseComponent },
  { path: 'create-workout', component: CreateWorkoutComponent },
  { path: 'list-workouts', component: ListWorkoutsComponent},
  { path: 'list-exercises', component: ListExercisesComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
