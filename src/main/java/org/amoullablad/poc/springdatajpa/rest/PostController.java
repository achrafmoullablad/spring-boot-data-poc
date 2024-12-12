package org.amoullablad.poc.springdatajpa.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.amoullablad.poc.springdatajpa.dto.Comment;
import org.amoullablad.poc.springdatajpa.dto.Post;
import org.amoullablad.poc.springdatajpa.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;

    @RequestMapping(
            method = {RequestMethod.PUT},
            produces = "application/json"
    )
    public ResponseEntity<String> add(@RequestBody Post post) {
        Long result = postService.addPost(post.getContent());
        return new ResponseEntity<>("created new post with id: "+result, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.PUT},
            produces = "application/json",
            value = "/{postId}/comment"
    )
    public ResponseEntity<String> addComment(@RequestBody Comment comment, @PathVariable Long postId) {
        Long result = postService.addComment(comment.getContent(), postId);
        return new ResponseEntity<>("created new comment with id: "+result, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = {RequestMethod.GET},
            produces = "application/json",
            value = "/{postId}"
    )
    public ResponseEntity<Post> get(@PathVariable Long postId) {
        Post result;
        try {
            result = postService.find(postId);
        } catch (Exception e) {
            log.warn(e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
