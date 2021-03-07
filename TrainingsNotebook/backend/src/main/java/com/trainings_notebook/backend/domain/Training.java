package com.trainings_notebook.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "category")
    @NotEmpty
    private TrainingCategories category;

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
