import { FormGroup } from "@angular/forms";
import { Workout } from "./Workout";

export class Exercise {

   id: number;
   name: string;
   type: string;
   description: string;
   reps?: number;
   duration?: number;
   workot_id: number;
  

  static mapFormGroupObjectToExercise(formGroupObject: FormGroup) {
    let newExercise = new Exercise();
    newExercise.name = formGroupObject.get('name').value;
    newExercise.type = formGroupObject.get('type').value;
    newExercise.reps = formGroupObject.get('reps').value;
    newExercise.duration = formGroupObject.get('duration').value;
    newExercise.description = formGroupObject.get('description').value;
    let exerciseWorkout: Workout = formGroupObject.get('workout').value;
    newExercise.workot_id = exerciseWorkout.id;

    return newExercise;
  }

}