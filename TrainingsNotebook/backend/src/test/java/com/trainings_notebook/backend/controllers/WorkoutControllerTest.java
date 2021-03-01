package com.trainings_notebook.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trainings_notebook.backend.domain.Workout;
import com.trainings_notebook.backend.service.WorkoutService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.validation.BindingResult;

import java.util.*;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = WorkoutController.class)
class WorkoutControllerTest extends AbstractRestControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    WorkoutService workoutService;

    Set<Workout> workouts;
    Workout workout1;
    Workout workout2;

    @BeforeEach
    void setUp() {
        workout1 = new Workout();
        workout1.setWorkout_id(1L);
        workout1.setName("pull ups");

        workout2 = new Workout();
        workout2.setWorkout_id(2L);
        workout2.setName("push ups");

        workouts = new HashSet<>();
        workouts.add(workout1);
        workouts.add(workout2);
    }

    @Test
    void getAllWorkouts() throws Exception {

        when(workoutService.findAll()).thenReturn(workouts);

        mockMvc.perform(get(WorkoutController.BASE_URL)
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    void getWorkoutByIdSuccess() throws Exception{
        when(workoutService.findById(1L)).thenReturn(workout1);

        mockMvc.perform(get(WorkoutController.BASE_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("pull ups")));
    }

    @Test
    void getWorkoutByIdFail() throws Exception {

        when(workoutService.findById(-1L)).thenReturn(null);

        mockMvc.perform(get(WorkoutController.BASE_URL + "/-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void addWorkoutSuccess() throws Exception{

        when(workoutService.save(workout2)).thenReturn(workout2);

        mockMvc.perform(post(WorkoutController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(workout2)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(2)))
                .andExpect(jsonPath("$.name", equalTo("push ups")));
    }

    @Test
    void addWorkoutFail() throws Exception {

        List<Workout> workoutList = Arrays.asList(workout1,workout2);
        Workout badWorkout = workoutList.get(1);
        badWorkout.setWorkout_id(null);
        badWorkout.setName(null);

        mockMvc.perform(post(WorkoutController.BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(badWorkout)))
                .andExpect(status().isBadRequest());

    }

    @Test
    void deleteWorkoutById() throws Exception {

        List<Workout> workoutList = Arrays.asList(workout1,workout2);
        given(workoutService.findById(2L)).willReturn(workoutList.get(0));

         mockMvc.perform(delete(WorkoutController.BASE_URL + "/2")
                .content(asJsonString(workout2))
                .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isNoContent());

    }
}