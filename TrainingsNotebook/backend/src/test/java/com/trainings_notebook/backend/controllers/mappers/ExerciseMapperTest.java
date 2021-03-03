package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.domain.dto.ExerciseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ExerciseMapperTest {

    @Autowired
    private ModelMapper modelMapper;
    Workout workout1;
    Exercise exercise1;
    ExerciseDTO exerciseDTO;

    @BeforeEach
    void setUp() {
        workout1 = Workout.builder()
                .id(1L)
                .name("Pull ups")
                .type("back")
                .description("body weight back workout")
                .build();
        exercise1 = Exercise.builder()
                .id(1L)
                .name("20 push ups")
                .type("chest")
                .reps(8)
                .description("20 push ups for 8 sets")
                .workout(workout1)
                .build();
        exerciseDTO = ExerciseDTO.builder()
                .id(1L)
                .name("20 push ups")
                .type("chest")
                .reps(8)
                .description("20 push ups for 8 sets")
                .workout_id(1L)
                .build();
    }

    @Test
    void convertToExerciseDTO() {
        ExerciseDTO exerciseDTO = modelMapper.map(exercise1, ExerciseDTO.class);
        exerciseDTO.setWorkout_id(exercise1.getWorkout().getId());
        assertEquals(exerciseDTO.getWorkout_id(), workout1.getId());
        assertEquals(exerciseDTO.getName(),exercise1.getName());
    }

    @Test
    void convertToEntity() {
        Exercise exercise = modelMapper.map(exerciseDTO, Exercise.class);
        exercise.setWorkout(workout1);
        assertEquals(exercise.getName(), exerciseDTO.getName());
        assertEquals(exercise.getWorkout().getId(), exerciseDTO.getWorkout_id());
    }
}