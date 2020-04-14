package com.bazalytskyi.coursework.repository;

import com.bazalytskyi.coursework.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);

    @Query("select u from UserEntity u " +
            "join u.marathons m " +
            "where m.id = ?1")
    List<UserEntity> findAllByMarathonId(Long marathonId);
}
