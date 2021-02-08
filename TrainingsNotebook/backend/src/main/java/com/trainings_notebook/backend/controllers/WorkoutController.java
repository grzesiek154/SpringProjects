package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.exceptions.ApiExceptionHandlers;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(WorkoutController.BASE_URL)
public class WorkoutController {

    public static final String BASE_URL = "/api/v1/workouts";

    private final WorkoutService workoutService;

    public WorkoutController(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @GetMapping
    public ResponseEntity<Set<Workout>> getAllWorkouts() {
        Set<Workout> workouts = workoutService.findAll();

        if(workouts.isEmpty()) {
            throw new ApiRequestException("Cannot get all workouts");
        }
        return new ResponseEntity<>(workouts, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Workout> getWorkoutById(@PathVariable Long id) {
        Workout workout = this.workoutService.findById(id);

        if(workout == null) {
            throw new ApiRequestException("Workout with id: " + id +" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workout,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Workout> addWorkout(@RequestBody @Valid Workout workout, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (workout == null)) {
            throw new ApiRequestException("Cannot add new workout, check your request.");
        }
        workoutService.save(workout);
        return new ResponseEntity<Workout>(workout, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Workout> updateWorkout(@RequestBody @Valid Workout workout, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || (workout == null)) {
            throw new ApiRequestException("Cannot add new workout, check your request.");
        }
        workoutService.save(workout);
        return new ResponseEntity<Workout>(workout, HttpStatus.OK);
    }
    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkoutById(@PathVariable Long workoutId) {
        Workout workout = workoutService.findById(workoutId);

        if(workout == null) {
            throw new ApiRequestException("Cannot delete workout with id: " + workoutId + ".");
        }
        workoutService.delete(workout);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
