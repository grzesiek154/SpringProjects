import { FormControl } from "@angular/forms";
import { Training } from "./Training";

export class CalendarDay {

  id: number;
  date: string;
  trainings: Training[] = [];
}