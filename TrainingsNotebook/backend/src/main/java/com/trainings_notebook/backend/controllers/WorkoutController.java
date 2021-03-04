package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.controllers.mappers.WorkoutMapper;
import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.domain.dto.WorkoutDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(WorkoutController.BASE_URL)
public class WorkoutController {

    public static final String BASE_URL = "/api/v1/workouts";

    private final WorkoutService workoutService;
    private final WorkoutMapper workoutMapper;

    public WorkoutController(WorkoutService workoutService, WorkoutMapper workoutMapper) {
        this.workoutService = workoutService;
        this.workoutMapper = workoutMapper;
    }

    @GetMapping
    public ResponseEntity<Set<WorkoutDTO>> getAllWorkouts() {
        Set<Workout> workouts = workoutService.findAll();
        Set<WorkoutDTO> workoutDTOSet = workouts.stream()
                .map(workout -> workoutMapper.convertToDTO(workout))
                .collect(Collectors.toSet());

        if (workouts.isEmpty() || workoutDTOSet.isEmpty()) {
            throw new ApiRequestException("Cannot get all workouts");
        }
        return new ResponseEntity<>(workoutDTOSet, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDTO> getWorkoutById(@PathVariable Long id) {
        Workout workout = this.workoutService.findById(id);
        WorkoutDTO workoutDTO = workoutMapper.convertToDTO(workout);

        if (workout == null || workoutDTO == null) {
            throw new ApiRequestException("Workout with id: " + id + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(workoutDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkoutDTO> addWorkout(@RequestBody @Valid WorkoutDTO workoutDTO, BindingResult bindingResult) {
        Workout workout = workoutMapper.convertToEntity(workoutDTO);
        if (bindingResult.hasErrors() || (workout == null)) {
            throw new ApiRequestException("Cannot add new workout, check your request.");
        }
        workoutService.save(workout);
        return new ResponseEntity<>(workoutDTO, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<WorkoutDTO> updateWorkout(@RequestBody @Valid WorkoutDTO workoutDTO, BindingResult bindingResult) {
        Workout workout = workoutMapper.convertToEntity(workoutDTO);
        if (bindingResult.hasErrors() || (workout == null)) {
            throw new ApiRequestException("Cannot add new workout, check your request.");
        }
        workoutService.save(workout);
        return new ResponseEntity<>(workoutDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{workoutId}")
    public ResponseEntity<Void> deleteWorkoutById(@PathVariable Long workoutId) {
        Workout workout = workoutService.findById(workoutId);

        if (workout == null) {
            throw new ApiRequestException("Cannot delete workout with id: " + workoutId + ".");
        }
        workoutService.delete(workout);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
