package org.amoullablad.poc.springdatajpa.mapper;


import org.amoullablad.poc.springdatajpa.dto.Comment;
import org.amoullablad.poc.springdatajpa.entity.CommentEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CommentMapper {

    Comment map(CommentEntity commentEntity);

    CommentEntity map(Comment post);
}