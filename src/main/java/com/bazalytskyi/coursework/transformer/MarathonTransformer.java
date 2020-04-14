package com.bazalytskyi.coursework.transformer;

import com.bazalytskyi.coursework.dto.MarathonDTO;
import com.bazalytskyi.coursework.entities.MarathonEntity;
import com.bazalytskyi.coursework.services.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarathonTransformer {
    @Autowired
    private SecurityUtils securityUtils;

    public MarathonEntity toEntity(MarathonDTO dto) {
        MarathonEntity entity = new MarathonEntity();
        return updateEntity(entity, dto);
    }

    public MarathonDTO toDto(MarathonEntity entity) {
        if (entity == null) {
            return null;
        }
        MarathonDTO dto = new MarathonDTO();
        dto.setId(entity.getId());
        dto.setArchive(entity.isArchive());
        dto.setAttendancies_count(entity.getUsers().size());
        dto.setAttendancies_max_count(entity.getAttendancesMaxCount());
        dto.setCategorу(entity.getCategory());
        dto.setDescription(entity.getDescription());
        dto.setEnd_dat(entity.getEndDate());
        dto.setFinished(entity.isFinished());
        dto.setHost_id(entity.getHostId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStart_date(entity.getStartDate());
        dto.setWinner(entity.getWinner());
        dto.setEnrolled(entity.getUsers().contains(securityUtils.getUser()));
        return dto;
    }

    public MarathonEntity updateEntity(MarathonEntity entity, MarathonDTO dto) {
        if (entity == null) {
            return null;
        }
        entity.setId(dto.getId());
        entity.setArchive(dto.isArchive());
        entity.setAttendancesMaxCount(dto.getAttendancies_max_count());
        entity.setCategory(dto.getCategorу());
        entity.setDescription(dto.getDescription());
        entity.setEndDate(dto.getEnd_dat());
        entity.setFinished(dto.isFinished());
        entity.setHostId(securityUtils.getUser().getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStartDate(dto.getStart_date());
        entity.setWinner(dto.getWinner());
        return entity;
    }

    public List<MarathonDTO> toDtoList(List<MarathonEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
