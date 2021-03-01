package com.trainings_notebook.backend.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity(name = "workouts")
@Table
public class Workout {

    public Workout(String name, String type,String description) {
        this.name = name;
        this.description = description;
        this.type = type;
    }

    public Workout() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_id;

    @Column(nullable = false)
    @NotEmpty
    private String name;

    @Column
    private String type;

    @Column
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "workout")
    @JsonManagedReference
    private List<Exercise> exercises;

    public Long getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(Long workout_id) {
        this.workout_id = workout_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
