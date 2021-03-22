import { ApplicationRef, ComponentFactoryResolver, ComponentRef, EmbeddedViewRef, Injectable, Injector } from '@angular/core';
import { DialogComponent } from './dialog/dialog.component';

@Injectable({
  providedIn: 'root'
})
export class DialogService {
  dialogComponentRef: ComponentRef<DialogComponent>

  constructor(
    private componentFactoryResolver: ComponentFactoryResolver,
    private appRef: ApplicationRef,
    private injector: Injector
  ) { }

  // To get the factory of our DialogComponent we can use the ComponentFactoryResolver provided by angular. This service is using the type of the component to look up the factory.
  appendDialogComponentToBody() {
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(DialogComponent);
    // Once we have the factory, we can use it to create an instance of our DialogComponent.
    const componentRef = componentFactory.create(this.injector);
    // We are passing in the injector we requested in the constructor. This enables the dynamic component to make use of dependency injection itself.
    this.appRef.attachView(componentRef.hostView);
    // Afterward, we need to attach the new component to the angular component tree (which is separate from the DOM). We do so by using the ApplicationRef we requested.
    const domElem = (componentRef.hostView as EmbeddedViewRef<any>).rootNodes[0] as HTMLElement;
    document.body.appendChild(domElem);
  
    this.dialogComponentRef = componentRef;
  }
}
