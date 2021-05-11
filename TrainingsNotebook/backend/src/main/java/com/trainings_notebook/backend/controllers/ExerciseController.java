package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.controllers.mappers.ExerciseMapper;
import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;
import com.trainings_notebook.backend.domain.dto.ExerciseDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ExerciseController.BASE_URL)
public class ExerciseController {

    public static final String BASE_URL = "/api/v1/exercises";

    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;

    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
    }

    @GetMapping
    public ResponseEntity<Set<ExerciseDTO>> getAllExercises() {
        Collection<Exercise> exercises = exerciseService.findAll();
        Set<ExerciseDTO> exerciseDTOSet = exercises.stream()
                .map(exercise -> exerciseMapper.convertToDTO(exercise))
                .collect(Collectors.toSet());

        if(exercises.isEmpty() || exerciseDTOSet.isEmpty()) {
            throw new ApiRequestException("Cannot get all exercises");
        }
        return new ResponseEntity<>(exerciseDTOSet, HttpStatus.OK);
    }

    @GetMapping("/{exerciseId}")
    public ResponseEntity<ExerciseDTO> getExerciseById(@PathVariable Long exerciseId) {
        Exercise exercise = this.exerciseService.findById(exerciseId);
        ExerciseDTO exerciseDTO = exerciseMapper.convertToDTO(exercise);

        if(exerciseDTO == null) {
            throw new ApiRequestException("Exercise with id: " + exerciseId +" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(exerciseDTO, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Set<ExerciseDTO>> getExercisesByCategory(@PathVariable String category) {
        Set<Exercise> exercisesByCategory = this.exerciseService.findByCategory(ExerciseCategories.valueOf(category.toUpperCase()));
        Set<ExerciseDTO> exercisesByCategoryDTO = exercisesByCategory.stream()
                .map(exercise -> exerciseMapper.convertToDTO(exercise))
                .collect(Collectors.toSet());

        if(exercisesByCategoryDTO == null) {
            throw new ApiRequestException("Exercises with category: " + category +" not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(exercisesByCategory, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Exercise> addExercise(@RequestBody @Valid ExerciseDTO exerciseDTO, BindingResult bindingResult) {
        Exercise exercise = exerciseMapper.convertToEntity(exerciseDTO);
        if(bindingResult.hasErrors() && exercise == null) {
            throw new ApiRequestException("Cannot add new exercise, check your request.");
        }
        exerciseService.save(exercise);
        return new ResponseEntity(exercise, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<Exercise> updateExercise(@RequestBody @Valid ExerciseDTO exerciseDTO, BindingResult bindingResult) {
        Exercise exercise = exerciseMapper.convertToEntity(exerciseDTO);
        if(bindingResult.hasErrors() || (exercise == null)) {
            throw new ApiRequestException("Cannot add new workout, check your request.");
        }
        exerciseService.save(exercise);
        return new ResponseEntity<>(exercise, HttpStatus.OK);
    }

    @DeleteMapping("/{exerciseId}")
    public ResponseEntity<Void> deleteExercise(@PathVariable Long exerciseId) {
        Exercise exercise = exerciseService.findById(exerciseId);
        if(exercise == null) {
            throw new ApiRequestException("Cannot delete workout with id: " + exerciseId + ".");
        }
        exerciseService.delete(exercise);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
