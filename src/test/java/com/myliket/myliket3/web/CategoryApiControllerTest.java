package com.myliket.myliket3.web;

import com.myliket.myliket3.domain.category.Category;
import com.myliket.myliket3.domain.category.CategoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@SpringBootTest
public class CategoryApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void allCategoryList() {
    }

    @Test
    public void getCategoryDetail() {
    }

    @Test
    public void Category_등록하기() {
        //given
        String name = "카테고리 이름";
        String state ="CY";


        Category categoryDto = Category.builder()
                .categoryId(UUID.randomUUID())
                .categoryName(name)
                .categoryState(state)
                .build();

        System.out.println("categoryDto:"+ categoryDto);

        String url ="http://localhost:" + port + "/categorys";
        System.out.println("url:"+ url);

        //when
        ResponseEntity<String> responseEntity = restTemplate.postForEntity( url,categoryDto, String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Category> all = categoryRepository.findAll();
//        assertThat(all.get(0).getCategoryName()).isEqualTo(name);



    }


    @Test
    public void updateCategory() {
    }

    @Test
    public void deleteCategory() {
    }
}