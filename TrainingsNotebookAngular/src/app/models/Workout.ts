import { FormGroup } from "@angular/forms";

export class Workout {

  id: number;
  name: string;
  description: string;
  type: string;


  static mapFormGroupObjectToWorkot (formGroupObject: FormGroup) {
    let newWorkout = new Workout();
    newWorkout.name = formGroupObject.get('name').value;
    newWorkout.type = formGroupObject.get('type').value;
    newWorkout.description = formGroupObject.get('description').value;
    return newWorkout;
  } 

}