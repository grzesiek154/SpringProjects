import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-map-test',
  templateUrl: './map-test.component.html',
  styleUrls: ['./map-test.component.css']
})
export class MapTestComponent implements OnInit {
  exampleMap = new Map();
  keyValue: string;
  mapKeys = ["value 1","value 2","value 3","value 4","value 5"]; 
  constructor() { }

  ngOnInit(): void {
    this.pupulateMapValues();
    console.log(this.exampleMap.get("value 2"));
  }


  pupulateMapValues() {
    this.exampleMap.set("value 1", [1,2,3]);
    this.exampleMap.set("value 2", [3,4,5]);
    this.exampleMap.set("value 3", [5,6,7]);
    this.exampleMap.set("value 4", [7,8,9]);
    this.exampleMap.set("value 5", [9,10,11]);
  }

  updateValueKey(newKey) {
    this.keyValue = newKey;
  }

}
