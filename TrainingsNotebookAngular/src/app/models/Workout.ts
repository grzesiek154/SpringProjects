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

  // get getId(): number {
  //   return this.id;
  // }

  // get getName(): string {
  //   return this.name;
  // }

  // get getType(): string {
  //   return this.type;
  // }

  // get getDescription() {
  //   return this.description;
  // }

  // set setId(id: number) {
  //   if(id != null && id > 0) {
  //     this.id = id;
  //   }
  // }

  // set setName(name: string) {
  //   if(name != null) {
  //     this.name = name;
  //   }
  // }

  // set setType(type: string) {
  //   if(type != null) {
  //     this.type = type;
  //   }
  // }

  // set setDescription(description: string) {
  //   if(description != null) {
  //     this.description = description
  //   }
  // }

}