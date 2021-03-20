import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent implements OnInit {
  @Input() config: any[] = [];
  // As our form is dynamic, we need to accept a configuration array in order to know what to create. To do this, we’re using an @Input() that accepts any array of objects.
  
  form: FormGroup;

  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.createGroup(); 
  }

  createGroup() {
    const group = this.fb.group({});
    this.config.forEach(control => group.addControl(control.name, this.fb.control({formState: ''})));
    return group;
  }

}
