package com.bazalytskyi.coursework.transformer;

import com.bazalytskyi.coursework.dto.PostDTO;
import com.bazalytskyi.coursework.entities.PostEntity;
import com.bazalytskyi.coursework.repository.MarathonRepository;
import com.bazalytskyi.coursework.repository.UserRepository;
import com.bazalytskyi.coursework.services.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostTransformer {
    @Autowired
    private MarathonRepository marathonRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SecurityUtils securityUtils;

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
        dto.setMarathon_name(entity.getMarathon() == null ? null : entity.getMarathon().getName());
        dto.setUser_id(entity.getUser().getId());
        dto.setCreated_date(entity.getCreatedDate());
        return dto;
    }

    public PostEntity updateEntity(PostEntity entity, PostDTO dto) {
        if (entity == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setText(dto.getText());
        entity.setMarathon(marathonRepository.findOne(dto.getMarathon_id()));
        entity.setUser(securityUtils.getUser());
        entity.setCreatedDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond());
        return entity;
    }

    public List<PostDTO> toDtoList(List<PostEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
