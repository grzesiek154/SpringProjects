package com.trainings_notebook.backend.domain;

import com.trainings_notebook.backend.repositories.ExerciseRepository;
import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


public class MockData implements CommandLineRunner {

    Workout workout1 = Workout.builder()
            .name("Pull ups")
            .description("body weight back workout")
            .build();

    Workout workout2 = Workout.builder()
            .name("Sit ups")
            .description("abs workout")
            .build();

    Workout workout3 = Workout.builder()
            .name("Push ups")
            .description("body weight chest workout")
            .build();



    Exercise exercise1 = Exercise.builder()
            .name("20 push ups")
            .category(ExerciseCategories.CHEST)
            .reps(8)
            .description("20 push ups for 8 sets")
            .workout(workout1)
            .build();

    Exercise exercise2 = Exercise.builder()
            .name("10 pull ups")
            .category(ExerciseCategories.BACK)
            .reps(8)
            .description("10 push ups for 8 sets")
            .workout(workout3)
            .build();

            //new Exercise("20 push ups","back","20 push ups for 8 sets",)


    private WorkoutRepository workoutRepository;
    private ExerciseRepository exerciseRepository;

    public MockData(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        workoutRepository.save(workout1);
        workoutRepository.save(workout2);
        workoutRepository.save(workout3);
        exerciseRepository.save(exercise1);
        exerciseRepository.save(exercise2);
    }
}
