package com.bazalytskyi.coursework.controllers;

import com.bazalytskyi.coursework.dto.MarathonDTO;
import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.entities.UserEntity;
import com.bazalytskyi.coursework.services.MarathonService;
import com.bazalytskyi.coursework.services.UserService;
import com.bazalytskyi.coursework.transformer.MarathonTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MarathonController {
    @Autowired
    private MarathonService marathonService;
    @Autowired
    private UserService userService;
    @Autowired
    private MarathonTransformer marathonTransformer;

    @GetMapping(value = "/marathon/all", produces = "application/json")
    public List<MarathonDTO> getAll() {
        return marathonService.getAll();
    }

    @GetMapping(value = "/marathon/{id}", produces = "application/json")
    public MarathonDTO getById(@PathVariable Long id) {
        return marathonService.getById(id);
    }

    @PostMapping(value = "/marathon", produces = "application/json")
    public MarathonDTO create(@RequestBody MarathonDTO dto) {
        return marathonService.save(dto);
    }

    @PutMapping(value = "/marathon", produces = "application/json")
    public MarathonDTO update(@RequestBody MarathonDTO dto) {
        return marathonService.update(dto);
    }

    @DeleteMapping(value = "/marathon/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        marathonService.deleteById(id);
    }

    @PostMapping(value = "/marathon/{marathonId}/enroll", produces = "application/json")
    public void enrollUser(@PathVariable Long marathonId) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        UserEntity user = userService.getUserByUsername(details.getUsername());
        marathonService.enrollUser(marathonId, user.getId());
    }

    @PostMapping(value = "/marathon/{marathonId}/exclude", produces = "application/json")
    public void excludeUser(@PathVariable Long marathonId) {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        UserEntity user = userService.getUserByUsername(details.getUsername());
        marathonService.excludeUser(marathonId, user.getId());
    }

    @GetMapping(value = "/marathon/{id}/post/all", produces = "application/json")
    public List<PostDTO> getMarathonPosts(@PathVariable Long id) {
        return marathonService.getMarathonPosts(id);
    }

    @GetMapping(value = "/marathon/createdByMe", produces = "application/json")
    public List<MarathonDTO> getMarathonByUserOwner() {
        return marathonService.getMarathonByUserOwner().stream()
                .map(marathonTransformer::toDto)
                .collect(Collectors.toList());
    }
}
