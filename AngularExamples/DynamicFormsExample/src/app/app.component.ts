import { Component } from '@angular/core';
import { Observable } from 'rxjs';
import { QuestionBase } from './models/question-base';
import { QuestionServiceService } from './services/question-service.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  questions$: Observable<QuestionBase<any>[]>;

  constructor(service: QuestionServiceService) {
    this.questions$ = service.getQuestions();
  }
}
