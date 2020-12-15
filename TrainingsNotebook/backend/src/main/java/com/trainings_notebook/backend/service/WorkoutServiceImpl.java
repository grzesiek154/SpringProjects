package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class WorkoutServiceImpl implements WorkoutService {

    private final WorkoutRepository workoutRepository;

    public WorkoutServiceImpl(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public Set<Workout> findAll() {
        Set<Workout> workouts = new HashSet<>();
        workoutRepository.findAll().forEach(workouts::add);
        return workouts;
    }

    @Override
    public Workout findById(Long aLong) {
        return workoutRepository.findById(aLong).orElse(null);
    }

    @Override
    public Workout save(Workout workout) {
        return workoutRepository.save(workout);
    }

    @Override
    public void delete(Workout workout) {
        workoutRepository.delete(workout);
    }

    @Override
    public void deleteById(Long aLong) {
        workoutRepository.deleteById(aLong);
    }
}
