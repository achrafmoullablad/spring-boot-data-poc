package org.amoullablad.poc.springdatajpa.repository;

import org.amoullablad.poc.springdatajpa.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> findByPost_Id(Long postId);

}
