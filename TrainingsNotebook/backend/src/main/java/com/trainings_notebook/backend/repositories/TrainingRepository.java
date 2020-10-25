package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Training;
import org.springframework.data.repository.CrudRepository;

public interface TrainingRepository extends CrudRepository<Training, Long> {
}
