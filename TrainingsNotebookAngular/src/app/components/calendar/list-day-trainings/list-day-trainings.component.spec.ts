import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListDayTrainingsComponent } from './list-day-trainings.component';

describe('ListDayTrainingsComponent', () => {
  let component: ListDayTrainingsComponent;
  let fixture: ComponentFixture<ListDayTrainingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListDayTrainingsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListDayTrainingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
