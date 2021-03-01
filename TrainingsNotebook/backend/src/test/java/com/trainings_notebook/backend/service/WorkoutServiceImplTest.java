package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;


@ExtendWith(MockitoExtension.class)
class WorkoutServiceImplTest {

    @Mock
    WorkoutRepository workoutRepository;
    WorkoutService workoutService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(workoutRepository);
        workoutService = new WorkoutServiceImpl(workoutRepository);
    }

    @Test
    void findAll() {
        Set<Workout> testSet = new HashSet<>();
        testSet.add(new Workout());
        testSet.add(new Workout());
        testSet.add(new Workout());
        testSet.add(new Workout());

        when(workoutRepository.findAll()).thenReturn(testSet);

        //when
        Set<Workout> workoutSet = workoutService.findAll();

        //then
        assertEquals(4, workoutSet.size());

    }

    @Test
    void findById() {
        Workout workout = new Workout();
        workout.setWorkout_id(1L);
        workout.setName("test workout");

        when(workoutRepository.findById(1L)).thenReturn(java.util.Optional.of(workout));
        //when
        Workout tempWorkout = workoutService.findById(1L);
        //then
        assertEquals("test workout", tempWorkout.getName());
    }

    @Test
    void save() {
        Workout savedWorkout = new Workout();
        savedWorkout.setWorkout_id(1L);
        savedWorkout.setName("pull ups");

        when(workoutRepository.save(any(Workout.class))).thenReturn(savedWorkout);
        //when
        Workout testWorkout = workoutService.save(savedWorkout);
        //then
        assertEquals(savedWorkout.getName(),testWorkout.getName());

    }

    @Test
    void delete() {
        Workout deletedWorkout = new Workout();
        deletedWorkout.setWorkout_id(1L);
        deletedWorkout.setName("push ups");

        workoutRepository.delete(deletedWorkout);
        verify(workoutRepository, times(1)).delete(deletedWorkout);
    }

    @Test
    void deleteById() {

        Long id = 1L;
        workoutRepository.deleteById(id);
        verify(workoutRepository, times(1)).deleteById(anyLong());
    }
}