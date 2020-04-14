package com.bazalytskyi.coursework.repository;

import com.bazalytskyi.coursework.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
    List<PostEntity> findAllByMarathonIdOrderByCreatedDateDesc(Long id);

    List<PostEntity> findAllByUserId(Long userId);

    @Query("select distinct pe from PostEntity pe " +
            "join pe.marathon m " +
            "join m.users u " +
            "where u.username = ?1 " +
            "order by pe.createdDate desc")
    List<PostEntity> findAllByUsersMarathon(String userEmail);
}