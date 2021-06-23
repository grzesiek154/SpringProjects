package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.CalendarDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CalendarDayRepository extends CrudRepository<CalendarDay, Long> {

    CalendarDay findByDate(String date);
}
