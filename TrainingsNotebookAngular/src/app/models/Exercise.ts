import { FormGroup } from "@angular/forms";
import { Workout } from "./Workout";

export class Exercise {
   id: number;
   name: string;
   category: string;
   description: string;
   reps?: number;
   duration?: number;
   workout: Workout;
  

  static mapFormGroupObjectToExercise(formGroupObject: FormGroup) {
    let newExercise = new Exercise();
    newExercise.name = formGroupObject.get('name').value;
    newExercise.category = formGroupObject.get('category').value;
    newExercise.reps = formGroupObject.get('reps').value;
    newExercise.duration = formGroupObject.get('duration').value;
    newExercise.description = formGroupObject.get('description').value;
    console.log("worout: " + formGroupObject.get('workout').value);
    newExercise.workout = formGroupObject.get('workout').value;


    return newExercise;
  }

}