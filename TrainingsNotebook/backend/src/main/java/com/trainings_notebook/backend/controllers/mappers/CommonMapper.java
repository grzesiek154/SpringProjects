package com.trainings_notebook.backend.controllers.mappers;

import com.trainings_notebook.backend.domain.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommonMapper<T, S> {

    private final ModelMapper modelMapper;

    public CommonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

}
