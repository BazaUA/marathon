package com.bazalytskyi.coursework.repository;

import com.bazalytskyi.coursework.entities.MarathonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarathonRepository extends JpaRepository<MarathonEntity, Long> {

}