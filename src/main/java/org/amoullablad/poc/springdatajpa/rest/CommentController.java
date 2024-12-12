package org.amoullablad.poc.springdatajpa.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.amoullablad.poc.springdatajpa.dto.Comment;
import org.amoullablad.poc.springdatajpa.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @RequestMapping(
            method = {RequestMethod.GET},
            produces = "application/json",
            value = "/{commentId}"
    )
    public ResponseEntity<Comment> get(@PathVariable Long commentId) {
        Comment result;
        try {
            result = commentService.find(commentId);
        } catch (Exception e) {
            log.warn(e.toString(), e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
