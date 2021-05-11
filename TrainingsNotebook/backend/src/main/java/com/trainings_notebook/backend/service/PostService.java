package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Post;
import com.trainings_notebook.backend.domain.dto.PostDTO;

import java.util.List;

public interface PostService extends CrudService<PostDTO, Long> {

    List<PostDTO> getPostsByUserName(String username);
}
