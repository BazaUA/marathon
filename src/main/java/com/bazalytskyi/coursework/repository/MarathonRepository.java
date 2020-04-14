package com.bazalytskyi.coursework.repository;

import com.bazalytskyi.coursework.entities.MarathonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarathonRepository extends JpaRepository<MarathonEntity, Long> {
    List<MarathonEntity> getAllByHostId(Long id);
}