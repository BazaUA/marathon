package com.bazalytskyi.coursework.services;

import com.bazalytskyi.coursework.entities.UserEntity;
import com.bazalytskyi.coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
    @Autowired
    private UserRepository userRepository;

    public UserEntity getUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        UserEntity userEntity = userRepository.findByUsername(userDetails.getUsername());
        return userEntity;
    }
}
