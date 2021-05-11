package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.domain.TrainingCategories;
import com.trainings_notebook.backend.repositories.TrainingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TrainingServiceImplTest {

    @Mock
    TrainingRepository trainingRepository;
    TrainingService trainingService;
    Set<Training> trainingSet;
    Training testTraining;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        trainingService = new TrainingServiceImpl(trainingRepository);

        trainingSet = new HashSet<>();
        trainingSet.add(new Training());
        trainingSet.add(new Training());
        trainingSet.add(new Training());
        trainingSet.add(new Training());

        testTraining = new Training();
        testTraining.setId(1L);
        testTraining.setName("cardio training");
        testTraining.setCategory(TrainingCategories.CARDIO);

    }

    @Test
    void findAll() {
        when(trainingRepository.findAll()).thenReturn(trainingSet);

        Collection<Training> trainings = trainingService.findAll();
        assertEquals(4, trainings.size());
    }

    @Test
    void findById() {
        when(trainingRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(testTraining));

        Training foundTraining = trainingService.findById(1L);
        assertEquals(foundTraining.getName(), testTraining.getName());
    }

    @Test
    void save() {
        when(trainingRepository.save(any(Training.class))).thenReturn(testTraining);

        Training savedTraining = trainingService.save(testTraining);
        assertEquals(savedTraining.getName(), testTraining.getName());
        assertEquals(savedTraining.getCategory(), testTraining.getCategory());
    }

    @Test
    void delete() {
        trainingRepository.delete(testTraining);
        verify(trainingRepository, times(1)).delete(testTraining);
    }

    @Test
    void deleteById() {
        trainingRepository.deleteById(1L);
        verify(trainingRepository, times(1)).deleteById(anyLong());
    }
}