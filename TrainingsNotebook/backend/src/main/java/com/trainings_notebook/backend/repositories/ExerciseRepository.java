package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    Set<Exercise> findByCategory(ExerciseCategories category);
}
