package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Training;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface TrainingRepository extends CrudRepository<Training, Long> {
    Set<Training> findByCategory(String category);
}
