package com.trainings_notebook.backend.repositories;

import com.trainings_notebook.backend.domain.Comment;
import com.trainings_notebook.backend.domain.Post;
import com.trainings_notebook.backend.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
    List<Comment> findByUser(User user);
}
