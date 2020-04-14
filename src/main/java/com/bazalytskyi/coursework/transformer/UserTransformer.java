package com.bazalytskyi.coursework.transformer;

import com.bazalytskyi.coursework.dto.UserDto;
import com.bazalytskyi.coursework.entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {
    public UserDto toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        UserDto dto = new UserDto();
        dto.setEmail(entity.getEmail());
        dto.setUsername(entity.getUsername());

        return dto;
    }
}
