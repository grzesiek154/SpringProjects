package com.trainings_notebook.backend.domain.dto;

import com.trainings_notebook.backend.domain.Training;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalendarDayDTO {

    private Long id;
    private String date;
    //private List<TrainingDTO> trainingsId;
    private List<TrainingDTO> trainings;
}
