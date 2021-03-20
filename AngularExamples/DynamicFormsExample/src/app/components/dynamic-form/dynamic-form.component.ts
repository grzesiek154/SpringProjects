import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { QuestionBase } from 'src/app/models/question-base';
import { QuestionControlService } from 'src/app/services/question-control.service';

@Component({
  selector: 'app-dynamic-form',
  templateUrl: './dynamic-form.component.html',
  styleUrls: ['./dynamic-form.component.css']
})
export class DynamicFormComponent implements OnInit {

  @Input() questions: QuestionBase<string>[] = [];
  form: FormGroup;
  payLoad = '';

  constructor(private qcs: QuestionControlService) { }

  ngOnInit(): void {
    this.form = this.qcs.toFormGroup(this.questions);
  }

  onSubmit() {
    this.payLoad = JSON.stringify(this.form.getRawValue());
  }

}
