package com.grzesiek.RedditClone.mapper;

import com.grzesiek.RedditClone.dto.SubredditDto;
import com.grzesiek.RedditClone.dto.SubredditDto.SubredditDtoBuilder;
import com.grzesiek.RedditClone.model.Subreddit;
import com.grzesiek.RedditClone.model.Subreddit.SubredditBuilder;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-12-30T16:29:15+0100",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.8 (AdoptOpenJDK)"
)
@Component
public class SubredditMapperImpl implements SubredditMapper {

    @Override
    public SubredditDto mapSubredditToDto(Subreddit subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditDtoBuilder subredditDto = SubredditDto.builder();

        subredditDto.id( subreddit.getId() );
        subredditDto.name( subreddit.getName() );
        subredditDto.description( subreddit.getDescription() );

        subredditDto.postCount( mapPosts(subreddit.getPosts()) );

        return subredditDto.build();
    }

    @Override
    public Subreddit mapDtoToSubreddit(SubredditDto subreddit) {
        if ( subreddit == null ) {
            return null;
        }

        SubredditBuilder subreddit1 = Subreddit.builder();

        subreddit1.id( subreddit.getId() );
        subreddit1.name( subreddit.getName() );
        subreddit1.description( subreddit.getDescription() );

        return subreddit1.build();
    }
}
