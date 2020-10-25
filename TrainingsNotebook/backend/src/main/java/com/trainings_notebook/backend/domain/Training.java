package com.trainings_notebook.backend.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Trainings")
@Table
public class Training {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private LocalDateTime date;


    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "trainings_exercises",
            joinColumns = @JoinColumn(name = "trainingId"),
            inverseJoinColumns = @JoinColumn(name = "exerciseId")
    )
    private List<Exercise> trainingExercises;

}
