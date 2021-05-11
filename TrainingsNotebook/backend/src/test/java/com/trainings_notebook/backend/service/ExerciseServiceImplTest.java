package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.ExerciseCategories;
import com.trainings_notebook.backend.repositories.ExerciseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExerciseServiceImplTest {

    @Mock
    ExerciseRepository exerciseRepository;
    ExerciseService exerciseService;
    Set<Exercise> testSet;
    Exercise exercise;

    @BeforeEach
    void setUp() {
        testSet = new HashSet<>();
        testSet.add(new Exercise());
        testSet.add(new Exercise());
        testSet.add(new Exercise());
        testSet.add(new Exercise());
        testSet.add(new Exercise());

        exercise = new Exercise();
        exercise.setId(1L);
        exercise.setName("exercise1");
        exercise.setCategory(ExerciseCategories.ABS);

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
        when(exerciseRepository.findById(1L)).thenReturn(java.util.Optional.of(exercise));

        //when
        Exercise testExercise = exerciseService.findById(1L);
        //then
        assertEquals("exercise1", testExercise.getName());
        assertEquals(ExerciseCategories.ABS, testExercise.getCategory());

    }

    @Test
    void save() {
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(exercise);

        //when
        Exercise testExercise = exerciseService.save(exercise);
        //then
        assertEquals(testExercise.getName(), exercise.getName());
        assertEquals(testExercise.getCategory(), exercise.getCategory());
    }

    @Test
    void delete() {
        exerciseRepository.delete(exercise);
        verify(exerciseRepository, times(1)).delete(exercise);
    }

    @Test
    void deleteById() {
        exerciseRepository.deleteById(1L);
        verify(exerciseRepository, times(1)).deleteById(anyLong());
    }
}