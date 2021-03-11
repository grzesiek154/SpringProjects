package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;
import com.trainings_notebook.backend.domain.dto.ExerciseDTO;
import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {

    private final ModelMapper modelMapper;
    private final WorkoutRepository workoutRepository;
    private final WorkoutMapper workoutMapper;

    public ExerciseMapper(ModelMapper modelMapper, WorkoutRepository workoutRepository, WorkoutMapper workoutMapper) {
        this.modelMapper = modelMapper;
        this.workoutRepository = workoutRepository;
        this.workoutMapper = workoutMapper;
    }

    Converter<String,ExerciseCategories> toEnum = new Converter<String, ExerciseCategories>() {
    @Override
    public ExerciseCategories convert(MappingContext<String, ExerciseCategories> context) {
        return ExerciseCategories.valueOf(context.getSource().toUpperCase());
    }
};
    public ExerciseDTO convertToDTO(Exercise exercise) {
        ExerciseDTO exerciseDTO = modelMapper.map(exercise, ExerciseDTO.class);
        exerciseDTO.setCategory(exercise.getCategory().toString());
        exerciseDTO.setWorkout(workoutMapper.convertToDTO(exercise.getWorkout()));
        return exerciseDTO;
    }

    public Exercise convertToEntity(ExerciseDTO exerciseDTO) {
        modelMapper.typeMap(ExerciseDTO.class, Exercise.class).addMapping(ExerciseDTO::getCategory,Exercise::setCategory);
        modelMapper.addConverter(toEnum);
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
        exercise.setWorkout(workoutMapper.convertToEntity(exerciseDTO.getWorkout()));
        return exercise;
    }
}
