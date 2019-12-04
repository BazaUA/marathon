package com.bazalytskyi.coursework.transformer;

import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.entities.PostEntity;
import com.bazalytskyi.coursework.repository.MarathonRepository;
import com.bazalytskyi.coursework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostTransformer {
    @Autowired
    private MarathonRepository marathonRepository;
    @Autowired
    private UserRepository userRepository;

    public PostEntity toEntity(PostDTO dto) {
        PostEntity entity = new PostEntity();
        return updateEntity(entity, dto);
    }

    public PostDTO toDto(PostEntity entity) {
        if (entity == null) {
            return null;
        }
        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setText(entity.getText());
        dto.setMarathon_id(entity.getMarathon() == null ? 0 : entity.getMarathon().getId());
        return dto;
    }

    public PostEntity updateEntity(PostEntity entity, PostDTO dto) {
        if (entity == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setMarathon(marathonRepository.findOne(dto.getMarathon_id()));
        entity.setUser(userRepository.findOne(dto.getUser_id()));
        return entity;
    }

    public List<PostDTO> toDtoList(List<PostEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
