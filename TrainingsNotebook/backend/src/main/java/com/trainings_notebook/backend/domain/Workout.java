package com.trainings_notebook.backend.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Workout {

    public Workout(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Workout() {
    }

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workout")
    private List<Exercise> exercises;
}
