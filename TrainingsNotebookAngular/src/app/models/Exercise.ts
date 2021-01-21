import { FormGroup } from "@angular/forms";
import { Workout } from "./Workout";

export class Exercise {

  private _id: number = Math.random();
  private _name: string;
  private _type: string;
  private _description: string;
  private _reps?: number;
  private _duration?: number;
  private _workout: Workout;
  

  static mapFormGroupObjectToExercise(formGroupObject: FormGroup) {
    let newExercise = new Exercise();
    newExercise._name = formGroupObject.get('name').value;
    newExercise._type = formGroupObject.get('type').value;
    newExercise._reps = formGroupObject.get('reps').value;
    newExercise._duration = formGroupObject.get('duration').value;
    newExercise._description = formGroupObject.get('description').value;
    newExercise._workout = formGroupObject.get('workout').value;

    return newExercise;
  }

  get name(): string {
    return this._name;
  }

  get type(): string {
    return this._type;
  }

  get description() {
    return this._description;
  }
  get reps() {
    return this._reps;
  }
  get duration() {
    return this._duration;
  }

  get workout() {
    return this._workout
  }

  set name(name: string) {
    if(name != null) {
      this._name = name;
    }
  }

  set type(type: string) {
    if(type != null) {
      this._type = type;
    }
  }

  set description(description: string) {
    if(description != null) {
      this._description = description
    }
  }

  set reps(reps: number) {
    if(reps != null && reps > 0) {
      this._reps = reps;
    } 
  }

  set duration(duration: number) {
    if(duration != null && duration > 0) {
      this._duration = duration;
    } 
  }

  set workout(workout: Workout) {
    if(workout != null) {
      this._workout = workout;
    }
  }
}