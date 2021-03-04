package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.domain.dto.WorkoutDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WorkoutMapper {

    private final ModelMapper modelMapper;

    public WorkoutMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public WorkoutDTO convertToDTO(Workout workout) {
        WorkoutDTO workoutDTO = modelMapper.map(workout, WorkoutDTO.class);
        return workoutDTO;
    }

    public Workout convertToEntity(WorkoutDTO workoutDTO) {
        Workout workout = modelMapper.map(workoutDTO, Workout.class);
        return workout;
    }
}
