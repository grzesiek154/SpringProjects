package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController()
@RequestMapping(ExerciseController.BASE_URL)
public class ExerciseController {

    public static final String BASE_URL = "/api/v1/exercises";

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<Exercise>> getAllExercises() {
        Set<Exercise> exercises = exerciseService.findAll();

        if(exercises.isEmpty()) {
            throw new ApiRequestException("Cannot get all exercises");
        }
        return new ResponseEntity<>(exercises, HttpStatus.OK);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<Exercise> getExerciseById(@PathVariable Long exerciseId) {
        Exercise exercise = this.exerciseService.findById(exerciseId);

        if(exercise == null) {
            throw new ApiRequestException("Exercise with id: " + exerciseId +" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody @Valid Exercise exercise, BindingResult bindingResult) {
        if(bindingResult.hasErrors() && exercise == null) {
            throw new ApiRequestException("Cannot add new exercise, check your request.");
        }
        exerciseService.save(exercise);
        return new ResponseEntity<>(exercise, HttpStatus.CREATED);
    }
    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long exerciseId) {
        Exercise exercise = exerciseService.findById(exerciseId);

        if(exercise == null) {
            throw new ApiRequestException("Cannot delete workout with id: " + exerciseId + ".");
        }
        exerciseService.delete(exercise);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
