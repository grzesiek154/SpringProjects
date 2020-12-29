package com.grzesiek.RedditClone.mapper;

import com.grzesiek.RedditClone.dto.PostRequest;
import com.grzesiek.RedditClone.dto.PostResponse;
import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.Subreddit;
import com.grzesiek.RedditClone.model.User;
import com.grzesiek.RedditClone.repository.CommentRepo;
import com.grzesiek.RedditClone.repository.VoteRepo;
import com.grzesiek.RedditClone.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepo commentRepository;
    @Autowired
    private VoteRepo voteRepository;
    @Autowired
    private AuthService authService;

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }
//    I changed PostMapper.java from an interface to abstract class, this is because we need information to map the new fields (voteCount, commentCount and duration).
//    As I said before, instead of updating PostService.java class with this logic, we can just change the interface to abstract class and inject the needed dependencies into our class to fill the new field’s information.
//    At line 30 – You can observe that the voteCount is set to a constant value – 0. This is because whenever we want to create a new Post object, we set the default vote count as 0.
//    When mapping from Post to PostResponse, we explicitly need to map only the fields commentCount (handled through commentCount() method) and duration (handled through getDuration())
//    The getDuration() is using a library called TimeAgo. This is a java library that shows us the dates in the relative Time Ago format.
}
