package com.trainings_notebook.backend.domain.dto;

import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.Workout;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExerciseDTO {

    private Long id;

    private String name;

    private String type;

    private String description;

    private int reps;

    private int duration;

    private Workout workout;


}
