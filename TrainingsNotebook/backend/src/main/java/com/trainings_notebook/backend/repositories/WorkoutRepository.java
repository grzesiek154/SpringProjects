package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;


public interface WorkoutRepository extends CrudRepository<Workout, Long> {



}
