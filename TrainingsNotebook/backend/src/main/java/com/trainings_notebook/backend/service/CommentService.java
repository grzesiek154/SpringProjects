package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.dto.CommentDTO;

import java.util.List;

public interface CommentService extends CrudService<CommentDTO, Long> {

    List<CommentDTO> findByPost(Long id);
    List<CommentDTO> findAllByUser(String username);
}
