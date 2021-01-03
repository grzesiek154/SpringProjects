package com.grzesiek.RedditClone.repository;

import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.Subreddit;
import com.grzesiek.RedditClone.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostRepo extends JpaRepository<Post, Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
