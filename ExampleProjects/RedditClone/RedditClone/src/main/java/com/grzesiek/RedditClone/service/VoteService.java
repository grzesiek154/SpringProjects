package com.grzesiek.RedditClone.service;

import com.grzesiek.RedditClone.dto.VoteDto;
import com.grzesiek.RedditClone.exceptions.PostNotFoundException;
import com.grzesiek.RedditClone.exceptions.SpringRedditException;
import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.Vote;
import com.grzesiek.RedditClone.model.VoteType;
import com.grzesiek.RedditClone.repository.PostRepo;
import com.grzesiek.RedditClone.repository.VoteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    private final VoteRepo voteRepository;
    private final PostRepo postRepository;
    private final AuthService authService;

    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(()-> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));
        Optional<Vote> voteByPostAndUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() && voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() + "'d for this post");
        }
        if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
            post.setVoteCount(post.getVoteCount() + 1);
        } else {
            post.setVoteCount(post.getVoteCount() - 1);
        }
        voteRepository.save(mapToVote(voteDto,post));
        postRepository.save(post);
    }
    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
