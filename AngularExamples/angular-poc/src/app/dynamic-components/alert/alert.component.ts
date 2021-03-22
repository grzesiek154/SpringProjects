import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { trigger, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-alert',
  templateUrl: './alert.component.html',
  styleUrls: ['./alert.component.css'],
  animations: [
    trigger('fadeInOut', [
      transition(':enter', [
        style({ opacity: 0 }),
        animate(500, style({ opacity: 1 }))
      ]),
      transition(':leave', [
        animate(500, style({ opacity: 0 }))
      ])
    ])
  ],
})
export class AlertComponent implements OnInit {
  @Input() type: string = "success";
  @Output() output = new EventEmitter();
  constructor() { }

  ngOnInit(): void {
  }

}
