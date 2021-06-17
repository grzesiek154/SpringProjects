package com.trainings_notebook.backend.service;


import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;

import java.time.Instant;
import java.util.List;

public interface CalendarDayService extends CrudService<CalendarDayDTO, Long> {

    CalendarDayDTO findByDate(Instant date);

}
