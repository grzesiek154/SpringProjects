package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.TrainingCategories;
import com.trainings_notebook.backend.domain.dto.ExerciseDTO;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;


@Component
public class TrainingMapper {

    private final ModelMapper modelMapper;
    private final ExerciseMapper exerciseMapper;


    public TrainingMapper(ModelMapper modelMapper, ExerciseMapper exerciseMapper) {
        this.modelMapper = modelMapper;
        this.exerciseMapper = exerciseMapper;
    }

    public TrainingDTO convertToDTO(Training training) {
        TrainingDTO trainingDTO = modelMapper.map(training, TrainingDTO.class);
        List<ExerciseDTO> exerciseDTOList = training.getTrainingExercises().stream()
                .map(exercise -> exerciseMapper.convertToDTO(exercise))
                .collect(Collectors.toList());
        trainingDTO.setExercises(exerciseDTOList);
       // trainingDTO.setCategory(training.getCategory().toString());

        return trainingDTO;
    }

    public Training convertToEntity(TrainingDTO trainingDTO) {
        Training training = modelMapper.map(trainingDTO, Training.class);
        List<Exercise> exerciseList = trainingDTO.getExercises().stream()
                .map(exerciseDTO -> exerciseMapper.convertToEntity(exerciseDTO))
                .collect(Collectors.toList());
        training.setTrainingExercises(exerciseList);
        //training.setCategory(TrainingCategories.valueOf(trainingDTO.getCategory().toUpperCase()));

        return training;
    }
}
