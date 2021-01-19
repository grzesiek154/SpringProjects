import { FormGroup } from "@angular/forms";

export class Training {
    
    private _id: number;
    private _name: string;
    private _type: string;
    private _createDate: number;
    private _description: string;

    static mapFormGroupObjectToTraining(formGroupObject: FormGroup) {
        let newTraining = new Training();
        
    }
}