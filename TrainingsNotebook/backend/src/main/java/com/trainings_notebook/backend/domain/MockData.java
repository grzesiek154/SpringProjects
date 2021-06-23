package com.trainings_notebook.backend.domain;

import com.trainings_notebook.backend.repositories.*;
import io.swagger.models.auth.In;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
public class MockData implements CommandLineRunner {

    String pattern = "yyyy-MM-dd";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

    User janek123 = new User.UserBuilder()
            .username("janek123")
            .email("janek123@gmail.com")
            .created(Instant.now())
            .password("asdzxc")
            .enabled(true)
            .build();


    Workout workout1 = Workout.builder()
            .name("Pull ups")
            .description("body weight back workout")
            .build();

    Workout workout2 = Workout.builder()
            .name("Sit ups")
            .description("abs workout")
            .build();
    Workout squats = Workout.builder()
            .name("Squats")
            .description("legs workout")
            .build();
    Workout deadLift = Workout.builder()
            .name("Deadlifts")
            .description("back workout")
            .build();
    Workout benchPress = Workout.builder()
            .name("Bench press")
            .build();
    Workout running = Workout.builder()
            .name("Running")
            .description("cardio")
            .build();
    Workout handstandPushUp= Workout.builder()
            .name("Handstand push up")
            .description("crossfit workout")
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
            .category(ExerciseCategories.SHOULDERS)
            .reps(8)
            .description("10 pull ups for 8 sets")
            .workout(workout3)
            .build();
    Exercise squatsExercise = Exercise.builder()
            .name("squats 100kg")
            .category(ExerciseCategories.LEGS)
            .reps(10)
            .workout(squats)
            .build();
    Exercise deadLiftExercise = Exercise.builder()
            .name("dead lifts 120kg")
            .category(ExerciseCategories.BACK)
            .reps(10)
            .workout(deadLift)
            .build();
    Exercise benchPressExercise = Exercise.builder()
            .name("bench press 80kg")
            .category(ExerciseCategories.CHEST)
            .reps(20)
            .workout(benchPress)
            .build();
    Exercise runningExercise = Exercise.builder()
            .name("running exercise")
            .category(ExerciseCategories.CARDIO)
            .duration(30)
            .workout(running)
            .build();
    Exercise handstandPushUpExercise = Exercise.builder()
            .name("Handstand Push Up")
            .category(ExerciseCategories.SHOULDERS)
            .reps(8)
            .workout(handstandPushUp)
            .build();

    List<Exercise> trainingsExerciseList = List.of(exercise1,exercise2);

    Training training1 = Training.builder()
            .name("Strenght training")
            .category(TrainingCategories.STRENGTH)
            .date(LocalDateTime.now())
            .trainingExercises(trainingsExerciseList)
            .build();

    CalendarDay calendarDay = CalendarDay.builder()
            .date(simpleDateFormat.format(Date.from(Instant.now())))
            .trainings(List.of(training1))
            .build();


    private WorkoutRepository workoutRepository;
    private ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final CalendarDayRepository calendarDayRepository;
    private final UserRepository userRepository;

    public MockData(WorkoutRepository workoutRepository, ExerciseRepository exerciseRepository, TrainingRepository trainingRepository, CalendarDayRepository calendarDayRepository, UserRepository userRepository) {
        this.workoutRepository = workoutRepository;
        this.exerciseRepository = exerciseRepository;
        this.trainingRepository = trainingRepository;
        this.calendarDayRepository = calendarDayRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        workoutRepository.saveAll(List.of(workout1,workout2,workout3,deadLift,benchPress,running,squats,handstandPushUp));
        exerciseRepository.saveAll(List.of(exercise1,exercise2,deadLiftExercise,benchPressExercise,runningExercise,squatsExercise, handstandPushUpExercise));

        trainingRepository.save(training1);
        calendarDayRepository.save(calendarDay);
        userRepository.save(janek123);
    }
}
