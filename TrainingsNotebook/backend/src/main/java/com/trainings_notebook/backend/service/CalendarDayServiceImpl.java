package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.controllers.mappers.CalendarDayMapper;
import com.trainings_notebook.backend.controllers.mappers.TrainingMapper;
import com.trainings_notebook.backend.domain.CalendarDay;
import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;
import com.trainings_notebook.backend.exceptions.SpringNotebookException;
import com.trainings_notebook.backend.repositories.CalendarDayRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CalendarDayServiceImpl implements CalendarDayService{

    private CalendarDayMapper calendarDayMapper;
    private CalendarDayRepository calendarDayRepository;
    private TrainingMapper trainingMapper;

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
        String newDateFormat = calendarDayDTO.getDate().substring(0, 10);
        CalendarDay calendarDay = calendarDayMapper.convertToEntity(calendarDayDTO);
        CalendarDay dayByDate = calendarDayRepository.findByDate(newDateFormat);

        if(dayByDate != null) {
           List<Training> trainings = dayByDate.getTrainings();
           calendarDay.getTrainings().forEach(trainings::add);
        } else {
            calendarDay.setDate(newDateFormat);
            calendarDayRepository.save(calendarDay);
        }
        return calendarDayDTO;
    }

    @Override
    public List<TrainingDTO> getTrainingsInDay(String date) {
        String newDateFormat = date.substring(0, 10);
        CalendarDay calendarDay = calendarDayRepository.findByDate(newDateFormat);
        List<TrainingDTO> trainingsInDay;
        if(calendarDay != null) {
           trainingsInDay = calendarDay.getTrainings().stream().map(training -> trainingMapper.convertToDTO(training)).collect(Collectors.toList());
        } else {
           trainingsInDay = new ArrayList<>();
        }
        return trainingsInDay;
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
