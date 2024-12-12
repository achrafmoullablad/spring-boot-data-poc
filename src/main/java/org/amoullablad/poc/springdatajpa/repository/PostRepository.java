package org.amoullablad.poc.springdatajpa.repository;

import org.amoullablad.poc.springdatajpa.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

}
