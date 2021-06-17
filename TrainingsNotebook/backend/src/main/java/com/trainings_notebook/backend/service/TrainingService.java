package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.CalendarDay;
import com.trainings_notebook.backend.domain.Training;

import java.util.List;
import java.util.Set;

public interface TrainingService extends CrudService<Training, Long> {

    Set<Training> findByCategory(String category);
    List<Training> findByCalendarDaysTrainings(CalendarDay calendarDay);
}
