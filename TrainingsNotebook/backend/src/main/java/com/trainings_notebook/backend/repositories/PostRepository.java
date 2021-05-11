package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Post;
import com.trainings_notebook.backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {

    List<Post> findByUser(User user);
}
