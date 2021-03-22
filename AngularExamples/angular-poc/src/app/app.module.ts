import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MapTestComponent } from './components/map-test/map-test.component';
import { AlertComponent } from './dynamic-components/alert/alert.component';
import { DynamicComponentAppComponent } from './dynamic-components/dynamic-component-app/dynamic-component-app.component';


@NgModule({
  declarations: [
    AppComponent,
    MapTestComponent,
    AlertComponent,
    DynamicComponentAppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
