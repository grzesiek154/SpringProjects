export class Workout {

  id: number;
  name: string;
  description: string;
  type: string;

  constructor(name?: string, type?: string, description?: string) {
   this.id = Math.random();
   this.name = name;
   this.type = type;
   this.description = description;
   
  }
}