package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;
import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.repositories.ExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceImplTest {

    @Mock
    ExerciseRepository exerciseRepository;
    ExerciseService exerciseService;
    Set<Exercise> testSet;
    Exercise mainExercise;
    Workout exerciseWorkout;

    @BeforeEach
    void setUp() {
        testSet = new HashSet<>();
        testSet.add(new Exercise());
        testSet.add(new Exercise());
        testSet.add(new Exercise());
        testSet.add(new Exercise());
        testSet.add(new Exercise());

        exerciseWorkout = Workout.builder()
                .id(1L)
                .name("workout 1")
                .description("test workout 1")
                .build();

        mainExercise = new Exercise();
        mainExercise.setId(1L);
        mainExercise.setName("exercise1");
        mainExercise.setCategory(ExerciseCategories.ABS);
        mainExercise.setWorkout(exerciseWorkout);

        MockitoAnnotations.initMocks(exerciseRepository);
        exerciseService = new ExerciseServiceImpl(exerciseRepository);
    }

    @Test
    void findAll() {
        when(exerciseRepository.findAll()).thenReturn(testSet);
        //When
        Collection<Exercise> exerciseSet = exerciseService.findAll();
        //then
        assertEquals(5, exerciseSet.size());
    }

    @Test
    void findById() {
        when(exerciseRepository.findById(1L)).thenReturn(java.util.Optional.of(mainExercise));

        //when
        Exercise testExercise = exerciseService.findById(1L);
        //then
        assertEquals("exercise1", testExercise.getName());
        assertEquals(ExerciseCategories.ABS, testExercise.getCategory());
        assertEquals(testExercise.getWorkout().getId(), exerciseWorkout.getId());

    }

    @Test
    void save() {
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(mainExercise);

        //when
        Exercise testExercise = exerciseService.save(mainExercise);
        //then
        assertEquals(testExercise.getName(), mainExercise.getName());
        assertEquals(testExercise.getCategory(), mainExercise.getCategory());
    }

    @Test
    void delete() {
        exerciseRepository.delete(mainExercise);
        verify(exerciseRepository, times(1)).delete(mainExercise);
    }

    @Test
    void deleteById() {
        exerciseRepository.deleteById(1L);
        verify(exerciseRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void findByCategory() {
        when(exerciseRepository.findByCategory(ExerciseCategories.ABS)).thenReturn(List.of(mainExercise));


        //when
        List<Exercise> testExercises = exerciseService.findByCategory(ExerciseCategories.ABS);
        List<Exercise> testExercises2 = exerciseRepository.findByCategory(ExerciseCategories.CHEST);
        List<Exercise> backExercises = exerciseRepository.findByCategory(ExerciseCategories.BACK);
        List<Exercise> shouldersExercises = exerciseService.findByCategory(ExerciseCategories.BACK);
        //then
        assertEquals(testExercises.get(0).getId(), mainExercise.getId());
        assertEquals(testExercises.get(0).getWorkout().getId(), mainExercise.getWorkout().getId());
        assertEquals(testExercises.get(0).getWorkout().getName(), mainExercise.getWorkout().getName());
       // assertEquals(testExercises2.get(0).getWorkout().getName(), "20 push ups");

    }
}