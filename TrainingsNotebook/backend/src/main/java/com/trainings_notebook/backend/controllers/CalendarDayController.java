package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.dto.CalendarDayDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.CalendarDayService;
import com.trainings_notebook.backend.service.CalendarDayServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(CalendarDayController.BASE_URL)
public class CalendarDayController {

    public static final String BASE_URL = "/api/calendarDays";
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
