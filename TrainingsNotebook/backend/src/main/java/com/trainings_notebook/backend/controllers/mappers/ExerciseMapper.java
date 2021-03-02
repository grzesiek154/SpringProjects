package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.domain.dto.ExerciseDTO;
import com.trainings_notebook.backend.repositories.ExerciseRepository;
import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    private final ModelMapper modelMapper;
    private final WorkoutRepository workoutRepository;

    public ExerciseMapper(ModelMapper modelMapper, WorkoutRepository workoutRepository) {
        this.modelMapper = modelMapper;
        this.workoutRepository = workoutRepository;
    }


    public ExerciseDTO convertToDTO(Exercise exercise) {
        ExerciseDTO exerciseDTO = modelMapper.map(exercise, ExerciseDTO.class);
        exerciseDTO.setWorkout_id(exercise.getWorkout().getId());
        return exerciseDTO;
    }

    public Exercise convertToEntity(ExerciseDTO exerciseDTO) {
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
        Workout workout = workoutRepository.findById(exerciseDTO.getWorkout_id()).orElse(null);
        if(workout != null) {
            exercise.setWorkout(workout);
        }
        return exercise;
    }
}
