package com.grzesiek.RedditClone.service;

import com.grzesiek.RedditClone.dto.SubredditDto;
import com.grzesiek.RedditClone.exceptions.SubredditNotFoundException;
import com.grzesiek.RedditClone.mapper.SubredditMapper;
import com.grzesiek.RedditClone.model.Subreddit;
import com.grzesiek.RedditClone.repository.SubredditRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

import java.util.List;

@Service
@AllArgsConstructor
public class SubredditService {

    private final SubredditRepo subredditRepo;
    private final AuthService authService;
    private final SubredditMapper subredditMapper;

    @Transactional(readOnly = true)
//    Reading methods like findAll() and findOne(…) are using @Transactional(readOnly = true) which is not strictly necessary but triggers a few optimizations in the transaction infrastructure (setting the FlushMode to MANUAL to let persistence providers potentially skip dirty checks when closing the EntityManager). Beyond that the flag is set on the JDBC Connection as well which causes further optimizations on that level.
    public List<SubredditDto> getAll() {
        return subredditRepo.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepo.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepo.findById(id)
                .orElseThrow(() -> new SubredditNotFoundException("Subreddit not found with id -" + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }






}
