package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;

import java.util.List;

public interface ExerciseService extends CrudService<Exercise, Long>{
    List<Exercise> findByCategory(ExerciseCategories category);
}
