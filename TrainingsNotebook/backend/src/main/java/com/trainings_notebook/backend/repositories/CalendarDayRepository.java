package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.CalendarDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CalendarDayRepository extends CrudRepository<CalendarDay, Long> {

    CalendarDay findByDate(Instant date);
}
