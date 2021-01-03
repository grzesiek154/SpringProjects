package com.grzesiek.RedditClone.repository;

import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.User;
import com.grzesiek.RedditClone.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
