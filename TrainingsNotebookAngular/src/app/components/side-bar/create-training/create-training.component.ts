import { Component, ComponentFactory, ComponentFactoryResolver, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Training } from 'src/app/models/Training';
import { TrainingsService } from 'src/app/services/trainings.service';
import { TrainingExerciseComponent } from '../../training/training-exercise/training-exercise.component';

@Component({
  selector: 'app-create-training',
  templateUrl: './create-training.component.html',
  styleUrls: ['./create-training.component.css']
})
export class CreateTrainingComponent implements OnInit {
  createTrainingFormGroup: FormGroup;
  currentTraining: Training;
  @ViewChild("trainingExerciseContainer", { read: ViewContainerRef }) container;
  componentRef: ComponentRef<any>;

  constructor(private router: Router, private trainingsService: TrainingsService, private fb: FormBuilder, private resolver: ComponentFactoryResolver) {
    this.createTrainingFormGroup = this.fb.group({
      name: ['', Validators.required],
      category: [''],
      description: [''],
      exercisesFormArray: this.fb.array([])
    });
    
  }

  ngOnInit(): void {
    this.clearTraining();
  }

  saveTraining() {
    this.currentTraining = Training.mapFormGroupObjectToTraining(this.createTrainingFormGroup as FormGroup);
    this.trainingsService.addTraining(this.currentTraining).subscribe(() => {
      this.backToTrainingsList();
    });  
  }

  clearTraining() {
    this.currentTraining = new Training();
  }

  backToTrainingsList() {
    this.router.navigateByUrl("/list-trainings");
  }
  createTrainingExerciseComponent() {
    const factory: ComponentFactory<any> = this.resolver.resolveComponentFactory(TrainingExerciseComponent);
    this.componentRef = this.container.createComponent(factory);
  }
  addExercise(exercise: FormGroup) {
    this.exercisesFormArray.push(exercise) ;
    console.log(exercise);
  }
  get exercisesFormArray() {
    return this.createTrainingFormGroup.get('exercisesFormArray') as FormArray;
  }
  printAll() {
    this.router.navigateByUrl('/list-trainings');
  }
}
