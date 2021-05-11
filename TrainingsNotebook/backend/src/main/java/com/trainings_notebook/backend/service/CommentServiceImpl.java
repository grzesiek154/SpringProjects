package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.Comment;
import com.trainings_notebook.backend.domain.Post;
import com.trainings_notebook.backend.domain.User;
import com.trainings_notebook.backend.domain.dto.CommentDTO;
import com.trainings_notebook.backend.exceptions.SpringNotebookException;
import com.trainings_notebook.backend.repositories.CommentRepository;
import com.trainings_notebook.backend.repositories.PostRepository;
import com.trainings_notebook.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CommentServiceImpl implements CommentService {

    private final ModelMapper commentMapper;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentDTO> findAll() {
        List<CommentDTO> comments = new LinkedList<>();
        commentRepository.findAll().forEach(comment -> comments.add(commentMapper.map(comment, CommentDTO.class)));
        return comments;
    }

    @Override
    public CommentDTO findById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new SpringNotebookException(id.toString()));
        return commentMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO save(CommentDTO commentDTO) {
        Post post = postRepository.findById(commentDTO.getPostId()).orElseThrow(() -> new SpringNotebookException("Post wit id: " + commentDTO.getPostId() + " not found"));
        String tempUserName = "janek123";
//        User user = userRepository.findByUsername(commentDTO.getUserName()).orElseThrow(()-> new EntityNotFoundException("Cannot find user wit username: " + commentDTO.getUserName()));
        User user = userRepository.findByUsername(tempUserName).orElseThrow(()-> new EntityNotFoundException("Cannot find user wit username: " + commentDTO.getUserName()));
        Comment comment = commentMapper.map(commentDTO, Comment.class);
        comment.setPost(post);
        comment.setUser(user);
        comment.setCreatedDate(Instant.now());
        commentRepository.save(comment);

        return commentDTO;
    }

    @Override
    public void delete(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId()).orElseThrow(() -> new EntityNotFoundException("Cannot delete comment, not found"));
        commentRepository.delete(comment);
    }

    @Override
    public void deleteById(Long aLong) {
        // TODO: 4/24/2021  
    }

    @Override
    public List<CommentDTO> findByPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cannot find post with Id: " + id));
        return commentRepository.findByPost(post).stream().map(comment -> commentMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CommentDTO> findAllByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()-> new EntityNotFoundException("User with name: " + username + " not found"));
        return commentRepository.findByUser(user).stream().map(comment -> commentMapper.map(comment, CommentDTO.class)).collect(Collectors.toList());


    }
}
