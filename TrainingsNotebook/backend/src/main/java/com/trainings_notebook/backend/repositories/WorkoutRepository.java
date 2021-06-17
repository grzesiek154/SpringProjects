package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Workout;
import org.springframework.data.repository.CrudRepository;


public interface WorkoutRepository extends CrudRepository<Workout, Long> { }
