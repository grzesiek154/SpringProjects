package com.trainings_notebook.backend.controllers;

import com.trainings_notebook.backend.domain.dto.PostDTO;
import com.trainings_notebook.backend.exceptions.ApiRequestException;
import com.trainings_notebook.backend.service.PostServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostServiceImpl postServiceImpl;

    @PostMapping
    public ResponseEntity<PostDTO> addPost(@RequestBody PostDTO postDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new ApiRequestException("Cannot add new Post, check your request");
        }

        postServiceImpl.save(postDTO);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, BindingResult bindingResult) {
        PostDTO postToUpdate = postServiceImpl.findById(postDTO.getId());
        if(bindingResult.hasErrors() || postToUpdate == null) {
            throw new ApiRequestException("Cannot add new Post, check your request");
        }
        postToUpdate.setPostName(postDTO.getPostName());
        postToUpdate.setContent(postDTO.getContent());
        postServiceImpl.save(postToUpdate);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        Set<PostDTO> posts = postServiceImpl.findAll();
        if(posts.isEmpty()) {
            throw new ApiRequestException("Cannot get all posts");
        }
         return new ResponseEntity(posts, HttpStatus.OK);
    }

    @GetMapping("/by-id/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long postId) {
        PostDTO post = postServiceImpl.findById(postId);
        if(post == null) {
            throw new ApiRequestException("Cannot get post with Id: " + postId);
        }
        return new ResponseEntity(post, HttpStatus.OK);
    }

    @GetMapping("/by-user/{username}")
    public ResponseEntity<PostDTO> getPostsByUsername(@PathVariable String username) {
        List<PostDTO> postsByUserName = postServiceImpl.getPostsByUserName(username);
        if(postsByUserName.isEmpty()) {
            throw new ApiRequestException("Cannot get posts by user name: " + username);
        }
        return new ResponseEntity(postsByUserName, HttpStatus.OK);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        PostDTO postDTO = postServiceImpl.findById(postId);
        if(postId == null || postId == 0 || postDTO == null) {
            throw new ApiRequestException("Cannod delete post with Id: " + postId);
        }
        postServiceImpl.deleteById(postId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
