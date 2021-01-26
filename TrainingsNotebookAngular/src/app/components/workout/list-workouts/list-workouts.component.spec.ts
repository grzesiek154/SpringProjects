import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListWorkoutsComponent } from './list-workouts.component';

describe('ListWorkoutsComponent', () => {
  let component: ListWorkoutsComponent;
  let fixture: ComponentFixture<ListWorkoutsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListWorkoutsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListWorkoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
