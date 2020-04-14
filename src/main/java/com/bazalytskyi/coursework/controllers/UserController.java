package com.bazalytskyi.coursework.controllers;

import com.bazalytskyi.coursework.dto.MarathonDTO;
import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.dto.UserDto;
import com.bazalytskyi.coursework.entities.CustomUserDetails;
import com.bazalytskyi.coursework.entities.UserEntity;
import com.bazalytskyi.coursework.services.TokenAuthenticationService;
import com.bazalytskyi.coursework.services.UserService;
import com.bazalytskyi.coursework.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bazalytskyi.coursework.auth.TokenHandler.EXPIRATION_TIME;

@RestController
public class UserController {
    @Autowired
    TokenAuthenticationService tokenAuthenticationService;
    @Autowired
    UserService userService;
    @Autowired
    UserTransformer userTransformer;

    @PostMapping(value = "/signup", produces = "application/json")
    public ResponseEntity<Boolean> registerNewUserAccount(@RequestBody UserDto accountDto) {
        UserEntity flag = userService.registerNewUserAccount(accountDto);
        if (flag == null) {
            return new ResponseEntity<Boolean>(Boolean.FALSE, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.CREATED);
    }


    @PostMapping(value = "/signin", produces = "application/json")
    public HttpEntity<Map> login(HttpServletResponse response, @RequestBody Map<String, String> body) {
        String password = body.get("password");
        String username = body.get("username");
        UserEntity userEntity = userService.getUserByLoginAndPassword(username, password);
        Map<String, Object> result = new HashMap<String, Object>();
        if (userEntity == null) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } else {
            CustomUserDetails user = new CustomUserDetails(userEntity);
            String token = tokenAuthenticationService.addAuthentication(response, user);
            result.put("token", token);
            result.put("expiration", EXPIRATION_TIME);
            return new HttpEntity(result);
        }
    }

    @GetMapping(value = "/home", produces = "application/json")
    public HttpEntity<Map> home(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Map<String, Boolean> authorizations = new HashMap();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                authorizations.put(grantedAuthority.getAuthority(), Boolean.TRUE);
            }
            result.put("permissions", authorizations);
        }
        try {
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
            result.put("username", user.getUsername());
            return new HttpEntity(result);
        } catch (ClassCastException ex) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/user/marathons", produces = "application/json")
    public List<MarathonDTO> getUserMarathon() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        UserEntity user = userService.getUserByUsername(details.getUsername());
        return userService.getUserMarathons(user.getId());
    }

    @GetMapping(value = "/user/posts", produces = "application/json")
    public List<PostDTO> getUserPostMarathon() {
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        UserEntity user = userService.getUserByUsername(details.getUsername());
        return userService.getUserPosts(user.getId());
    }

    @GetMapping(value = "/marathon/{id}/enrolledUsers", produces = "application/json")
    public List<UserDto> getMarathonEnrolledUser(@PathVariable Long id) {
        return userService.getEnrolledUserByMarathonId(id).stream()
                .map(userTransformer::toDto)
                .collect(Collectors.toList());
    }
}
