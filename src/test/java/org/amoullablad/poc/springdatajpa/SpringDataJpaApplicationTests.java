package org.amoullablad.poc.springdatajpa;

import org.amoullablad.poc.springdatajpa.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(properties = "spring.config.additional-location=classpath:application-h2.yml")
class SpringDataJpaApplicationTests {
	@Autowired
	PostRepository postRepository;

	@Test
	void contextLoads() {
		assertThat(postRepository).isNotNull();
	}

}
