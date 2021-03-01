package com.trainings_notebook.backend.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "type")
    @NotEmpty
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "reps")
    private int reps;

    @Column(name = "duration")
    private int duration;

    @ManyToMany(mappedBy = "trainingExercises")
    private List<Training> trainings;

    @ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "workout_id",referencedColumnName = "workout_id", nullable = false)
    @JsonBackReference
    private Workout workout;

}
