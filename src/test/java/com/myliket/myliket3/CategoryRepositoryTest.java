package com.myliket.myliket3;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myliket.myliket3.domain.Category;
import com.myliket.myliket3.domain.CategoryRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.IntStream;


@SpringBootTest
@Ignore
public class CategoryRepositoryTest {


    @Autowired
    CategoryRepository categoryRepository;

    @DisplayName("카테고리 등록 테스트")
    @Test
    public void testInsert()  {

        IntStream.rangeClosed(1,10).forEach(i -> {
            Category category = Category.builder()
                    .categoryId(UUID.randomUUID())
                    .categoryName("Test.."+ i)
                    .categoryState("CY")
                    .categoryCreatedAt(LocalDateTime.now())
                    .categoryUpdatedAt(LocalDateTime.now())
                    .build();

            categoryRepository.save(category);
        });

    }
}
