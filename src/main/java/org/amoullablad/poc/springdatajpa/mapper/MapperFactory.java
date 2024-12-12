package org.amoullablad.poc.springdatajpa.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperFactory {

    @Bean
    PostMapper postMapper(){
        return Mappers.getMapper(PostMapper.class);
    }

    @Bean
    CommentMapper commentMapper(){
        return Mappers.getMapper(CommentMapper.class);
    }
}
