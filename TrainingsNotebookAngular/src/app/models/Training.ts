import { FormGroup } from "@angular/forms";
import { Exercise } from "./Exercise";

export class Training {

    private _id: number = Math.random()
    private _name: string;
    private _type: string;
    private _description: string;
    private _exercises: Exercise[];
    private _isAvailable: boolean;

    static mapFormGroupObjectToTraining(formGroupObject: FormGroup) {
        let newTraining = new Training();
        return newTraining;
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

    get exercises() {
        return this._exercises;
    }

    get isAvailable() {
        return this.isAvailable;
    }

    set name(name: string) {
        if (name != null) {
            this._name = name;
        }
    }

    set type(type: string) {
        if (type != null) {
            this._type = type;
        }
    }

    set description(description: string) {
        if (description != null) {
            this._description = description
        }
    }

    set exercises(exercises: Exercise[]) {
        if (exercises != null && exercises.length > 0) {
            this._exercises = exercises;
        }
    }

    set isAvailable(isAvailable: boolean) {
        if (isAvailable != null) {
            this._isAvailable = isAvailable;
        }
    }
}