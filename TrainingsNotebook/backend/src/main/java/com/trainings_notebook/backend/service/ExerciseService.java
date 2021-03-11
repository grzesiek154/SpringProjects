package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;

import java.util.Set;

public interface ExerciseService extends CrudService<Exercise, Long>{
    Set<Exercise> findByCategory(ExerciseCategories category);
}
