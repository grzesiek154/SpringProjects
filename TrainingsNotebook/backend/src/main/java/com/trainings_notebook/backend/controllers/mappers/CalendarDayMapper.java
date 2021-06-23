package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.CalendarDay;
import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;
import com.trainings_notebook.backend.service.TrainingService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CalendarDayMapper {

    private final ModelMapper modelMapper;
    private final TrainingMapper trainingMapper;
    private final TrainingService trainingService;

    public CalendarDayMapper(ModelMapper modelMapper, TrainingMapper trainingMapper, TrainingService trainingService) {
        this.modelMapper = modelMapper;
        this.trainingMapper = trainingMapper;
        this.trainingService = trainingService;
    }

    public CalendarDayDTO convertToDTO(CalendarDay calendarDay) {
        CalendarDayDTO calendarDayDTO = modelMapper.map(calendarDay, CalendarDayDTO.class);

        List<TrainingDTO> trainingDTOS = calendarDay.getTrainings().stream().map(training -> trainingMapper.convertToDTO(training)).collect(Collectors.toList());
        calendarDayDTO.setTrainings(trainingDTOS);

        return calendarDayDTO;
    }

    public CalendarDay convertToEntity(CalendarDayDTO calendarDayDTO) {
        CalendarDay calendarDay = modelMapper.map(calendarDayDTO, CalendarDay.class);

        List<Training> trainings = calendarDayDTO.getTrainings().stream().map(trainingDTO -> trainingMapper.convertToEntity(trainingDTO)).collect(Collectors.toList());
        calendarDay.setTrainings(trainings);

        return calendarDay;
    }
}
