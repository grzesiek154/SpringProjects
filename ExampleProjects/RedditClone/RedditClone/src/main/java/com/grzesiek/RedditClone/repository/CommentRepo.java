package com.grzesiek.RedditClone.repository;

import com.grzesiek.RedditClone.model.Comment;
import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {

    List<Comment> findByPost(Post post);
    List<Comment> findAllByUser(User user);
}
