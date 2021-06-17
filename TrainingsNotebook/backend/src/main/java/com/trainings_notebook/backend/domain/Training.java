package com.trainings_notebook.backend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "trainings")
@Table
public class Training {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private TrainingCategories category;

    @Column(name = "date")
    private LocalDateTime date;


    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "trainings_exercises",
            joinColumns = @JoinColumn(name = "trainingId"),
            inverseJoinColumns = @JoinColumn(name = "exerciseId")
    )
    private List<Exercise> trainingExercises;


    @ManyToMany(mappedBy = "trainings")
    private List<CalendarDay> calendarDays;

}
