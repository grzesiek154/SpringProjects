package com.trainings_notebook.backend.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "workouts")
@Table
public class Workout {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

}


