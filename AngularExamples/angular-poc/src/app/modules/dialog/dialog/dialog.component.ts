import { Component, OnInit, Type } from '@angular/core';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {
  childComponentType: Type<any>;
  constructor() { }

  ngOnInit(): void {
  }
  ngAfterViewInit() {}

  ngOnDestroy() {}

  onOverlayClicked(evt: MouseEvent) {
    // close the dialog
  }

  onDialogClicked(evt: MouseEvent) {
    evt.stopPropagation()
  }

}
