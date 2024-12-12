package org.amoullablad.poc.springdatajpa.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.amoullablad.poc.springdatajpa.dto.Post;
import org.amoullablad.poc.springdatajpa.entity.CommentEntity;
import org.amoullablad.poc.springdatajpa.entity.PostEntity;
import org.amoullablad.poc.springdatajpa.mapper.PostMapper;
import org.amoullablad.poc.springdatajpa.repository.CommentRepository;
import org.amoullablad.poc.springdatajpa.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {
    private final PostRepository postRepository;

    private final CommentRepository commentRepository;

    private final PostMapper postMapper;

    public Long addPost(String content){
        PostEntity post = PostEntity.builder()
                .content(content)
                .build();
        PostEntity save = postRepository.save(post);
        log.info("Created a new post with id: {}", save.getId());
        return save.getId();
    }


    public Long addComment(String content, Long postId){
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("no post with id: " + postId));
        CommentEntity commentEntity = CommentEntity.builder()
                .content(content)
                .post(postEntity)
                .build();
        CommentEntity save = commentRepository.save(commentEntity);
        log.info("Created a new comment with id: {}", save.getId());
        return save.getId();
    }


    public Post find(Long id){
        PostEntity postEntity = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id " + id + " not present"));
        return postMapper.map(postEntity);
    }
}
