package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.ExerciseCategories;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T findById(ID id);

    T save(T object);

    void delete(T object);

    void deleteById(ID id);
}
