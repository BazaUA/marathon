package com.bazalytskyi.coursework.repository;

import com.bazalytskyi.coursework.entities.PrivilegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends JpaRepository<PrivilegeEntity,Long> {
    PrivilegeEntity findByName(String username);
}
