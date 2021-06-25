package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;
import com.trainings_notebook.backend.domain.dto.TrainingDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.CalendarDayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CalendarDayController.BASE_URL)
public class CalendarDayController {

    public static final String BASE_URL = "/api/v1/calendarDays";
    private final CalendarDayService calendarDayService;

    public CalendarDayController(CalendarDayService calendarDayService) {
        this.calendarDayService = calendarDayService;
    }

    @GetMapping
    public ResponseEntity<Set<CalendarDayDTO>> getAllCalendarDays() {
        Set<CalendarDayDTO> calendarDayDTOSet = calendarDayService.findAll().stream().collect(Collectors.toSet());
        return new ResponseEntity(calendarDayDTOSet, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CalendarDayDTO> addCalendarDay(@RequestBody @Valid CalendarDayDTO calendarDayDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ApiRequestException("Cannot add new training, check your request.");
        }
        CalendarDayDTO savedCalendarDay = calendarDayService.save(calendarDayDTO);

        return new ResponseEntity(savedCalendarDay, HttpStatus.CREATED);
    }


    @GetMapping("/{calendarDayId}")
    public ResponseEntity<CalendarDayDTO> getCalendarDayById(@PathVariable Long calendarDayId) {
        CalendarDayDTO calendarDayDTO = calendarDayService.findById(calendarDayId);

        if(calendarDayDTO == null) {
            throw new ApiRequestException("Calendar day with id: " + calendarDayId + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(calendarDayDTO,HttpStatus.OK);
    }

    @GetMapping("/numberOfTrainingsInDay/{date}")
    public ResponseEntity<Integer> getAmountOfTrainingsInADay(@PathVariable String date) {
        CalendarDayDTO calendarDayDTO = calendarDayService.findByDate(date);
        Integer numberOfTrainings = calendarDayDTO.getTrainings().size();

        if(calendarDayDTO == null) {
            throw new ApiRequestException("Calendar day with date: " + date + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(numberOfTrainings,HttpStatus.OK);
    }

    @GetMapping("/trainingsInDay/{date}")
    public ResponseEntity<List<TrainingDTO>> getTrainingsInADay(@PathVariable String date) {
        List<TrainingDTO> trainingsInDay = calendarDayService.getTrainingsInDay(date);

        if(trainingsInDay == null) {
            throw new ApiRequestException("Calendar day with date: " + date + " not found.", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(trainingsInDay,HttpStatus.OK);
    }

    @DeleteMapping("/{calendarDayId}")
    public ResponseEntity<Void> deleteCalendarDay(@PathVariable Long calendarDayId) {
        CalendarDayDTO calendarDayDTO = calendarDayService.findById(calendarDayId);

        if(calendarDayDTO == null) {
            throw new ApiRequestException("Calendar day with id: " + calendarDayId + " not found.", HttpStatus.NOT_FOUND);
        }
        calendarDayService.delete(calendarDayDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
