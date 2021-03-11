package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Training;
import com.trainings_notebook.backend.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public Set<Training> findAll() {
        Set<Training> trainings = new HashSet<>();
        trainingRepository.findAll().forEach(trainings::add);
        return trainings;
    }

    @Override
    public Training findById(Long aLong) {
        return trainingRepository.findById(aLong).orElse(null);
    }

    @Override
    public Training save(Training training) {
        return trainingRepository.save(training);
    }

    @Override
    public void delete(Training training) {
        delete(training);
    }

    @Override
    public void deleteById(Long aLong) {
        deleteById(aLong);
    }

    @Override
    public Set<Training> findByCategory(String category) {
        return trainingRepository.findByCategory(category);
    }
}
