package com.trainings_notebook.backend.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "trainings")
@Table
public class Training {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotEmpty
    private Long id;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "type")
    @NotEmpty
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Exercise> getTrainingExercises() {
        return trainingExercises;
    }

    public void setTrainingExercises(List<Exercise> trainingExercises) {
        this.trainingExercises = trainingExercises;
    }
}
