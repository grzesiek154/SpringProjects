export class Workout {

  id: number;
  name: string;
  description: string;

  constructor(name?: string, description?: string) {
   this.id = Math.random();
   this.name = name;
   this.description = description;
  }
}