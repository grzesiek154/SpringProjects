package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.dto.CommentDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments/")
@AllArgsConstructor
public class CommentsController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@RequestBody @Valid CommentDTO commentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiRequestException("Cannot add new comment, check your request.");
        }
        commentService.save(commentDTO);
        return new ResponseEntity(commentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{id}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForPost(@PathVariable("id") Long id) {
        List<CommentDTO> comments = commentService.findByPost(id);
        if(comments.isEmpty()) {
            throw  new ApiRequestException("Cannot get comments for post with id: " + id);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentDTO>> getAllCommentsForUser(@PathVariable("userName") String userName) {
        List<CommentDTO> comments = commentService.findAllByUser(userName);
        if(comments.isEmpty()) {
            throw  new ApiRequestException("Cannot get comments for user: " + userName);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

}
