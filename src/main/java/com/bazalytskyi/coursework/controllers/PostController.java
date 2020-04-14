package com.bazalytskyi.coursework.controllers;

import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.services.PostService;
import com.bazalytskyi.coursework.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostTransformer postTransformer;

    @GetMapping(value = "/post/{id}", produces = "application/json")
    public PostDTO getById(@PathVariable Long id) {
        return postService.getById(id);
    }

    @PostMapping(value = "/post", produces = "application/json")
    public PostDTO create(@RequestBody PostDTO dto) {
        return postService.create(dto);
    }

    @DeleteMapping(value = "/post/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

    @GetMapping(value = "/post/allByUsersMarathon", produces = "application/json")
    public List<PostDTO> getAllByUsersMarathon() {
        return postService.getAllByUsersMarathon().stream()
                .map(postTransformer::toDto)
                .collect(Collectors.toList());
    }
}
