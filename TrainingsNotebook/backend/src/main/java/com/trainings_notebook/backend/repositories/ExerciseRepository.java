package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Exercise;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
}
