import { FormGroup } from "@angular/forms";
import { Exercise } from "./Exercise";

export class Training {

    id: number;
    name: string;
    category: string;
    description: string;
    exercises: Exercise[] = [];
    isAvailable: boolean;
    

    static mapFormGroupObjectToTraining(formGroupObject: FormGroup) {
        if(formGroupObject != null) {
        let newTraining = new Training();
        newTraining.name = formGroupObject.get('name').value;
        newTraining.category = formGroupObject.get('category').value;
        newTraining.description = formGroupObject.get('description').value;
        formGroupObject.get('exercisesFormArray').value.forEach(exerciseControl => {
            newTraining.exercises.push(exerciseControl.value);
        })
        
        return newTraining;
        }
    }

}