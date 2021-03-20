import { ComponentFactoryResolver, Directive, Input, ViewContainerRef } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Directive({
  selector: '[dynamicField]',
})
export class DynamicFieldDirective {
  @Input() config;

  @Input() group: FormGroup;
  
  constructor(
    private resolver: ComponentFactoryResolver,
    private container: ViewContainerRef
  ) {}

  ngOnInit() {

  }
}