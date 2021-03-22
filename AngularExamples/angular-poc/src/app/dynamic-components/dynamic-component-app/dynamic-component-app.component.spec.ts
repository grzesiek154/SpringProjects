import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DynamicComponentAppComponent } from './dynamic-component-app.component';

describe('DynamicComponentAppComponent', () => {
  let component: DynamicComponentAppComponent;
  let fixture: ComponentFixture<DynamicComponentAppComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DynamicComponentAppComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DynamicComponentAppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
