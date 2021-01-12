import { Workout } from "./Workout";

export class Exercise {

  id: number;
  name: string;
  type: string;
  description: string;
  reps?: number;
  duration?: number;
  workout: Workout;
  
  // constructor(name: string, type: string, description: string, reps: number, workout: Workout) {
  //   this.name = name;
  //   this.type = type;
  //   this.description = description;
  //   this.reps = reps;
  //   this.workout = workout;
  // }

  // constructor(name: string, type: string, description: string, duration: number, workout: Workout) {
  //   this.name = name;
  //   this.type = type;
  //   this.description = description;
  //   this.duration = duration;
  //   this.workout = workout;
  // }
}