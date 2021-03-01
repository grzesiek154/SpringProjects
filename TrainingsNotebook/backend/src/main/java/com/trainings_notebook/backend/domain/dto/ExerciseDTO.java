package com.trainings_notebook.backend.domain.dto;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.Workout;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

public class ExerciseDTO {

    private Long id;

    private String name;

    private String type;

    private String description;

    private int reps;

    private int duration;

    private List<Training> trainings;

    private Workout workout;


}
