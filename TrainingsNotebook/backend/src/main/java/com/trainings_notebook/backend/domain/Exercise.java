package com.trainings_notebook.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "exercises")
@Table
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @NonNull
    private ExerciseCategories category;

    @Column(name = "description")
    private String description;

    @Column(name = "reps")
    private int reps;

    @Column(name = "duration")
    private int duration;

    @ManyToMany(mappedBy = "trainingExercises")
    private List<Training> trainings;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workout_id")
    @JsonBackReference
    private Workout workout;


}
