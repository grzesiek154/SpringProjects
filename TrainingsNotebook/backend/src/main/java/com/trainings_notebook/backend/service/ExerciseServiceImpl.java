package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.dto.ExerciseDTO;
import com.trainings_notebook.backend.repositories.ExerciseRepository;
import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository exerciseRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public Set<Exercise> findAll() {
        Set<Exercise> exercises = new HashSet<>();
        exerciseRepository.findAll().forEach(exercises::add);

        return exercises;
    }

    @Override
    public Exercise findById(Long aLong) {
        return exerciseRepository.findById(aLong).orElse(null);
    }

    @Override
    public Exercise save(Exercise exercise) {
        return exerciseRepository.save(exercise);
    }

    @Override
    public void delete(Exercise exercise) {
        exerciseRepository.delete(exercise);
    }

    @Override
    public void deleteById(Long aLong) {
        exerciseRepository.deleteById(aLong);
    }

//    private Exercise convertToExerciseEntity(ExerciseDTO exerciseDTO) {
//        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
//
//        if()
//    }
}
