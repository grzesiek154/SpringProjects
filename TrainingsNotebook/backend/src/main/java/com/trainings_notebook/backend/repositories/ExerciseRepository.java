package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findByCategory(ExerciseCategories category);
}
