package com.grzesiek.RedditClone.mapper;

import com.grzesiek.RedditClone.dto.CommentsDto;
import com.grzesiek.RedditClone.model.Comment;
import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentsDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentsDto commentsDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
//    Notice that when using Instant.now() inside the mapping for the field createDate we are using the fully qualified class name of Instant class. This is because as this expression is inside a String, Mapstruct is not able to recognize and import the relevant import statements for this class.
    CommentsDto mapToDto(Comment comment);
}
