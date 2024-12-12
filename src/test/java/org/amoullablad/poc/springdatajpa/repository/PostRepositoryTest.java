package org.amoullablad.poc.springdatajpa.repository;

import org.amoullablad.poc.springdatajpa.entity.CommentEntity;
import org.amoullablad.poc.springdatajpa.entity.PostEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.TestDatabaseAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@DataJpaTest(excludeAutoConfiguration = TestDatabaseAutoConfiguration.class)
@Sql({  "/sql/cleanup.sql",
        "/sql/populate.sql"
})
class PostRepositoryTest {

    @Container
    static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest");

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void findById(){
        PostEntity postEntity = postRepository.findById(1L).orElseThrow(RuntimeException::new);
        assertThat(postEntity).isNotNull();
        assertThat(postEntity.getContent()).isEqualTo("Red is the best color");
        assertThat(postEntity.getComments()).hasSize(2);
    }

    @Test
    void deletePost(){
        postRepository.deleteAll();
        List<CommentEntity> list = commentRepository.findByPost_Id(1L);
        assertThat(list).isEmpty();
    }
}