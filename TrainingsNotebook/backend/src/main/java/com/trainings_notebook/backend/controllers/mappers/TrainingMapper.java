package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;
import com.trainings_notebook.backend.service.TrainingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class TrainingMapper {

    private final ModelMapper modelMapper;
    private final TrainingService trainingService;

    public TrainingMapper(ModelMapper modelMapper, TrainingService trainingService) {
        this.modelMapper = modelMapper;
        this.trainingService = trainingService;
    }

    public TrainingDTO convertToDTO(Training training) {
        TrainingDTO trainingDTO = modelMapper.map(training, TrainingDTO.class);
        trainingDTO.setTrainingExercises(training.getTrainingExercises());

        return trainingDTO;
    }

//    public Training convertToEntity(TrainingDTO trainingDTO) {
//        Training training = modelMapper.map(trainingDTO, Training.class);
//        //training.
//    }
}
