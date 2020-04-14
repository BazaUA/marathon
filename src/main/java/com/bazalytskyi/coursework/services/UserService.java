package com.bazalytskyi.coursework.services;

import com.bazalytskyi.coursework.dto.MarathonDTO;
import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.repository.PostRepository;
import com.bazalytskyi.coursework.repository.RoleRepository;
import com.bazalytskyi.coursework.repository.UserRepository;
import com.bazalytskyi.coursework.dto.UserDto;
import com.bazalytskyi.coursework.entities.CustomUserDetails;
import com.bazalytskyi.coursework.entities.RoleEntity;
import com.bazalytskyi.coursework.entities.UserEntity;
import com.bazalytskyi.coursework.transformer.MarathonTransformer;
import com.bazalytskyi.coursework.transformer.PostTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    private MarathonTransformer marathonTransformer;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostTransformer postTransformer;


    public UserEntity registerNewUserAccount(UserDto accountDto){
        UserEntity user = new UserEntity();
        user.setUsername(accountDto.getUsername());
        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        user.setEmail(accountDto.getEmail());
        RoleEntity role_user = roleRepository.findByName("ROLE_USER");
        user.setRole(role_user);
        return userRepository.save(user);
    }

    public CustomUserDetails loadUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);
        CustomUserDetails user = new CustomUserDetails(userEntity);
        return user;
    }

    public UserEntity getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username);

        return userEntity;
    }

    public UserEntity getUserById(long id){
        return userRepository.getOne(id);
    }

    public UserEntity  getUserByLoginAndPassword(String username, String existingPassword) {
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity != null && passwordEncoder.matches(existingPassword, userEntity.getPassword())) {
            return userEntity;
        } else {
            return null;
        }
    }

    public List<MarathonDTO> getUserMarathons(Long userId) {
        UserEntity userEntity = userRepository.findOne(userId);
        if (userEntity == null) {
            return null;
        }
        return marathonTransformer.toDtoList(userEntity.getMarathons());
    }

    public List<PostDTO> getUserPosts(Long userId) {
        return postTransformer.toDtoList(postRepository.findAllByUserId(userId));
    }

    public List<UserEntity> getEnrolledUserByMarathonId(Long id) {
        return userRepository.findAllByMarathonId(id);
    }

    public void delete(List<UserEntity> users) {
        userRepository.delete(users);
    }
}