package com.trainings_notebook.backend.domain.dto;

import com.trainings_notebook.backend.domain.Exercise;
import com.trainings_notebook.backend.domain.TrainingCategories;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainingDTO {

    private Long id;
    private String name;
    private String category;
    private LocalDateTime date;
    private List<ExerciseDTO> trainingExercises;
}
