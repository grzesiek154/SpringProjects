package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.TrainingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping(TrainingController.BASE_URL)
public class TrainingController {

    public static final String BASE_URL = "/api/v1/trainings";

    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Training>> getAllTrainings() {
        Set<Training> trainings = trainingService.findAll();

        if(trainings.isEmpty()) {
            throw new ApiRequestException("Cannot get all trainings");
        }
        return new ResponseEntity<>(trainings, HttpStatus.OK);
    }

    @GetMapping("/{trainingId}")
    public ResponseEntity<Training> getTrainingById(@PathVariable Long trainingId) {
        Training training = this.trainingService.findById(trainingId);

        if(training == null) {
            throw new ApiRequestException("Training with id: " + trainingId + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(training,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Training> addTraining(@RequestBody @Valid Training training, BindingResult bindingResult) {
        if(bindingResult.hasErrors() && training == null) {
            throw new ApiRequestException("Cannot add new training, check your request.");
        }
        trainingService.save(training);
        return new ResponseEntity<>(training, HttpStatus.CREATED);
    }
    @DeleteMapping("/{trainingId}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Long trainingId) {
        Training training = trainingService.findById(trainingId);

        if(training == null) {
            throw new ApiRequestException("Cannot delete workout with id: " + trainingId + ".");
        }
        trainingService.delete(training);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
