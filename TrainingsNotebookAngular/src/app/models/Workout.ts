import { FormGroup } from "@angular/forms";

export class Workout {

  private _id: number;
  private _name: string;
  private _description: string;
  private _type: string;


  static mapFormGroupObjectToWorkot (formGroupObject: FormGroup) {
    let newWorkout = new Workout();
    newWorkout.name = formGroupObject.get('name').value;
    newWorkout.type = formGroupObject.get('type').value;
    newWorkout.description = formGroupObject.get('description').value;
    return newWorkout;
  } 

  get id(): number {
    return this._id;
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

  set id(id: number) {
    if(id != null && id > 0) {
      this._id = id;
    }
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

}