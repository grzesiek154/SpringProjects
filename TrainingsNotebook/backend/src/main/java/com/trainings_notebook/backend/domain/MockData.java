package com.trainings_notebook.backend.domain;

import com.trainings_notebook.backend.repositories.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


public class MockData implements CommandLineRunner {

    Workout workout1 = new Workout("Pull ups", "back", "body weight back workout");
    Workout workout2 = new Workout("Sit ups", "abs", "abs workout");
    Workout workout3 = new Workout("Push ups", "chest", "body weight chest workout");


    private WorkoutRepository workoutRepository;

    public MockData(WorkoutRepository workoutRepository) {
        this.workoutRepository = workoutRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        workoutRepository.save(workout1);
        workoutRepository.save(workout2);
        workoutRepository.save(workout3);
    }
}
