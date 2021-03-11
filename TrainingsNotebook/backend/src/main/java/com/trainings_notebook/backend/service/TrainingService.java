package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Training;

import java.util.Set;

public interface TrainingService extends CrudService<Training, Long> {

    Set<Training> findByCategory(String category);
}
