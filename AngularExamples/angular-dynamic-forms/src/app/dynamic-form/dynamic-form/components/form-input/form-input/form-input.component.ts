import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-form-input',
  templateUrl: './form-input.component.html',
  styleUrls: ['./form-input.component.css']
})
export class FormInputComponent implements OnInit {
  config;
  group: FormGroup;
  // You can see we’ve set two properties on the class for the config and group. However, these aren’t using @Input() like you would expect, because we aren’t going to be using this component in the traditional way
  
  constructor() { }

  ngOnInit(): void {
  }

}
