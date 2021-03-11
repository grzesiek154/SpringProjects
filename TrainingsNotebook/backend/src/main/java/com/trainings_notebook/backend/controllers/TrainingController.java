package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.controllers.mappers.TrainingMapper;
import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(TrainingController.BASE_URL)
public class TrainingController {

    public static final String BASE_URL = "/api/v1/trainings";

    private final TrainingService trainingService;
    private final TrainingMapper trainingMapper;

    public TrainingController(TrainingService trainingService, TrainingMapper trainingMapper) {
        this.trainingService = trainingService;
        this.trainingMapper = trainingMapper;
    }

    @GetMapping
    public ResponseEntity<Set<Training>> getAllTrainings() {
        Set<Training> trainings = trainingService.findAll();
        Set<TrainingDTO> trainingDTOSet = trainings.stream()
                .map(training -> trainingMapper.convertToDTO(training))
                .collect(Collectors.toSet());

        if(trainings.isEmpty()) {
            throw new ApiRequestException("Cannot get all trainings");
        }
        return new ResponseEntity(trainingDTOSet, HttpStatus.OK);
    }

    @GetMapping("/{trainingId}")
    public ResponseEntity<TrainingDTO> getTrainingById(@PathVariable Long trainingId) {
        Training training = this.trainingService.findById(trainingId);
        TrainingDTO trainingDTO = trainingMapper.convertToDTO(training);

        if(training == null) {
            throw new ApiRequestException("Training with id: " + trainingId + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(trainingDTO,HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<TrainingDTO> getTrainingsByCategory(@PathVariable String category) {
        Set<Training> trainings = trainingService.findByCategory(category);
        Set<TrainingDTO> trainingDTOSet = trainings.stream()
                .map(training -> trainingMapper.convertToDTO(training))
                .collect(Collectors.toSet());
        if(trainingDTOSet == null) {
            throw new ApiRequestException("Trainings with category: " + category + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(trainingDTOSet,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody @Valid TrainingDTO trainingDTO, BindingResult bindingResult) {
        Training training = trainingMapper.convertToEntity(trainingDTO);
        if(bindingResult.hasErrors() && training == null) {
            throw new ApiRequestException("Cannot add new training, check your request.");
        }
        trainingService.save(training);
        return new ResponseEntity(training, HttpStatus.CREATED);
    }
    @DeleteMapping("/{trainingId}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long trainingId) {
        Training training = trainingService.findById(trainingId);

        if(training == null) {
            throw new ApiRequestException("Cannot delete workout with id: " + trainingId + ".");
        }
        trainingService.delete(training);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
