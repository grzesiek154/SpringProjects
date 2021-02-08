package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Workout;

public interface WorkoutService extends CrudService<Workout, Long> {

    Workout updateWorkout(Workout workout);
}
