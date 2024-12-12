package org.amoullablad.poc.springdatajpa.mapper;

import org.amoullablad.poc.springdatajpa.dto.Post;
import org.amoullablad.poc.springdatajpa.entity.PostEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(collectionMappingStrategy =  CollectionMappingStrategy.ADDER_PREFERRED,
        uses = CommentMapper.class)
public interface PostMapper {

    Post map(PostEntity postEntity);

    PostEntity map(Post post);
}
