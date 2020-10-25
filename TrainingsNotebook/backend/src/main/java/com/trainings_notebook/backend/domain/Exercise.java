package com.trainings_notebook.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Exercise {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "reps")
    private int reps;

    @Column(name = "duration")
    private int duration;

    @ManyToMany(mappedBy = "trainingExercises")
    private List<Training> trainings;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "workoutId", nullable = false)
    private Workout workout;
}
