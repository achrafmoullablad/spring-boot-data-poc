package org.amoullablad.poc.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.amoullablad.poc.springdatajpa.dto.Comment;
import org.amoullablad.poc.springdatajpa.entity.CommentEntity;
import org.amoullablad.poc.springdatajpa.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment find(Long id){
        CommentEntity commentEntity = commentRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Id " + id + " not present"));
        return Comment.builder()
                .content(commentEntity.getContent())
                .build();
    }
}
