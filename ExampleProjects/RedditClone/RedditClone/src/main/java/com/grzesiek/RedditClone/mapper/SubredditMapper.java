package com.grzesiek.RedditClone.mapper;

import com.grzesiek.RedditClone.dto.SubredditDto;
import com.grzesiek.RedditClone.model.Post;
import com.grzesiek.RedditClone.model.Subreddit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
//First, we annotated the SubredditMapper with @Mapper(componentModel=’spring’) annotation to specify that this interface is a Mapstruct Mapper and Spring should identify it as a component and should be able to inject it into other components like SubredditService.
public interface SubredditMapper {

    @Mapping(target = "postCount", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto mapSubredditToDto(Subreddit subreddit);
//    The first method inside SubredditMapper class mapSubredditToDto(), contains only one @Mapping annotation for the target field numberOfPosts, in this case, we are mapping from List<Posts> to an Integer, this kind of mapping is not straight forward and we need to write our logic. We can do that by using the expression field and pass the method definition for mapPosts() which returns an Integer.
//Notice that for all remaining fields, the @Mapping annotation is not needed as they are implicitly added at compile time.

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subreddit);
//    We can create reverse mappings from SubredditDto to Subreddit by annotating a method with InheritInverseConfiguration. This annotation reverse’s the mapping which exists to convert from Subreddit to SubredditDto

}
