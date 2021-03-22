import { Component, ComponentFactory, ComponentFactoryResolver, ComponentRef, OnInit, ViewChild, ViewContainerRef } from '@angular/core';
import { AlertComponent } from '../alert/alert.component';

@Component({
  selector: 'app-dynamic-component-app',
  templateUrl: './dynamic-component-app.component.html',
  styleUrls: ['./dynamic-component-app.component.css']
})
export class DynamicComponentAppComponent implements OnInit {
  @ViewChild("alertContainer", { read: ViewContainerRef }) container;
  // As the name suggests, ViewContainerRef is a reference to a container. ViewContainerRef stores a reference to the template element ( our container ) and also exposes an API to create components.
  componentRef: ComponentRef<any>;
  constructor(private resolver: ComponentFactoryResolver) { }
// You can think of ComponentFactory as an object that knows how to create a component.
  ngOnInit(): void {
  }

  createComponent(type) {
    const factory: ComponentFactory<any> = this.resolver.resolveComponentFactory(AlertComponent);
    this.componentRef = this.container.createComponent(factory);    
    this.componentRef.instance.type = type;
    this.componentRef.instance.output.subscribe(event => console.log(event));
  }

  ngOnDestroy() {
    this.componentRef.destroy();    
  }
}
