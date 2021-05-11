package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.controllers.mappers.CommonMapper;
import com.trainings_notebook.backend.domain.Post;
import com.trainings_notebook.backend.domain.User;
import com.trainings_notebook.backend.domain.dto.PostDTO;
import com.trainings_notebook.backend.exceptions.SpringNotebookException;
import com.trainings_notebook.backend.repositories.PostRepository;
import com.trainings_notebook.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final ModelMapper postMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getPostsByUserName(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findByUser(user)
                .stream()
                .map(post -> postMapper.map(post, PostDTO.class))
                .collect(toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PostDTO> findAll() {
        Set<PostDTO> posts = new HashSet<>();
        postRepository.findAll().forEach(post -> posts.add(postMapper.map(post, PostDTO.class)));
        return posts;
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO findById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new SpringNotebookException("cannot find post with id: " + id.toString()));
        return postMapper.map(post,PostDTO.class);
    }

    @Override
    public PostDTO save(PostDTO postDTO) {
        User user = userRepository.findByUsername(postDTO.getUserName()).orElseThrow(()-> new EntityNotFoundException("Cannot find user wit username: " + postDTO.getUserName()));
        Post post = postMapper.map(postDTO, Post.class);
        post.setUser(user);
        post.setCreatedDate(Instant.now());
        postRepository.save(post);
        return postDTO;
    }

    @Override
    public void delete(PostDTO postDTO) {
        Post post  = postRepository.findById(postDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Cannot delete post"));
        postRepository.delete(post);

    }

    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }


}
