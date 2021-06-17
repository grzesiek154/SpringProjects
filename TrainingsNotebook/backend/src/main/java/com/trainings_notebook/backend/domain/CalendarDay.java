package com.trainings_notebook.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "calendar_day")
public class CalendarDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @NotNull
    private Instant date;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "calendar_day_trainings",
            joinColumns = @JoinColumn(name = "trainingId"),
            inverseJoinColumns = @JoinColumn(name = "calendarDayId")
    )
    private List<Training> trainings;
}
