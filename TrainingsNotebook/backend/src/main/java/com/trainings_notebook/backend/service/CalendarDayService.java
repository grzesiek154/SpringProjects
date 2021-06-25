package com.trainings_notebook.backend.service;


import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;

import java.time.Instant;
import java.util.List;

public interface CalendarDayService extends CrudService<CalendarDayDTO, Long> {

    CalendarDayDTO findByDate(String date);
    List<TrainingDTO> getTrainingsInDay(String date);

}
