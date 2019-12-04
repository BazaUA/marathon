package com.bazalytskyi.coursework.controllers;

import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

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
}
