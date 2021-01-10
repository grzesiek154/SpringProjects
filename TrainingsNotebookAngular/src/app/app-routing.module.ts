import { NgModule } from '@angular/core';
import { CreateTrainingComponent } from './components/side-bar/create-training/create-training.component';
import { RouterModule, Routes } from '@angular/router';



const routes: Routes = [
  { path: 'create-training', component: CreateTrainingComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
