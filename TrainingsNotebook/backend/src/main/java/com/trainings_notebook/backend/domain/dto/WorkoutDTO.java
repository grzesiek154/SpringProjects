package com.trainings_notebook.backend.domain.dto;

import com.trainings_notebook.backend.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkoutDTO {

    private Long id;
    private String name;
    private String Type;
    private String Description;
    private List<Exercise> exerciseDTOList;

}
