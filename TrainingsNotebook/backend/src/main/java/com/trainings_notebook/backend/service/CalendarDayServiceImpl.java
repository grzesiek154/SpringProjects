package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.controllers.mappers.CalendarDayMapper;
import com.trainings_notebook.backend.domain.CalendarDay;
import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;
import com.trainings_notebook.backend.exceptions.SpringNotebookException;
import com.trainings_notebook.backend.repositories.CalendarDayRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CalendarDayServiceImpl implements CalendarDayService{

    private CalendarDayMapper calendarDayMapper;
    private CalendarDayRepository calendarDayRepository;

    @Override
    public Set<CalendarDayDTO> findAll() {
        Set<CalendarDayDTO> calendarDayDTOS = new HashSet<>();
        calendarDayRepository.findAll().forEach(calendarDay -> calendarDayDTOS.add(calendarDayMapper.convertToDTO(calendarDay)));

        return calendarDayDTOS;
    }

    @Override
    public CalendarDayDTO findById(Long id) {
        CalendarDay calendarDay = calendarDayRepository.findById(id).orElseThrow(()-> new SpringNotebookException(id.toString()));
        return calendarDayMapper.convertToDTO(calendarDay);
    }

    @Override
    public CalendarDayDTO save(CalendarDayDTO calendarDayDTO) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        CalendarDay calendarDay = calendarDayMapper.convertToEntity(calendarDayDTO);

        calendarDay.setDate(simpleDateFormat.format(calendarDay.getDate()));
        calendarDayRepository.save(calendarDay);
        return calendarDayDTO;
    }

    @Override
    public void delete(CalendarDayDTO calendarDayDTO) {
        CalendarDay calendarDay = calendarDayRepository.findById(calendarDayDTO.getId()).orElseThrow(()-> new SpringNotebookException("Cannot delete, calendar day not found"));
        calendarDayRepository.delete(calendarDay);
    }

    @Override
    public void deleteById(Long id) {
        CalendarDay calendarDay = calendarDayRepository.findById(id).orElseThrow(()-> new SpringNotebookException("Cannot delete, calendar day not found"));
        calendarDayRepository.deleteById(id);
    }

    @Override
    public CalendarDayDTO findByDate(String date) {
       CalendarDay calendarDay = calendarDayRepository.findByDate(date);
       return calendarDayMapper.convertToDTO(calendarDay);
    }
}
