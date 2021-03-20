import { Input, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, ReactiveFormsModule } from '@angular/forms';
import { DynamicFormComponent } from './containers/dynamic-form/dynamic-form/dynamic-form.component';
import { FormInputComponent } from './components/form-input/form-input/form-input.component';
import { FormSelectComponent } from './components/form-select/form-select/form-select.component';
import { FormButtonComponent } from './components/form-button/form-button/form-button.component';



@NgModule({
  declarations: [DynamicFormComponent, FormInputComponent, FormSelectComponent, FormButtonComponent],
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [DynamicFormComponent]
})
export class DynamicFormModule { 
}
