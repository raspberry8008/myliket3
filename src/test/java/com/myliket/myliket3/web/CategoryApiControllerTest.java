package com.myliket.myliket3.web;

import com.myliket.myliket3.domain.dto.request.category.CategoryDto;
import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.category.CategoryRepository;
import com.myliket.myliket3.domain.entity.category.CategoryState;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void 카테고리_전체조회하기() {

        String url ="http://localhost:" + port + "/categorys";

        ResponseEntity<Category> responseEntity = restTemplate.getForEntity(url, Category.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 카테고리_상세조회하기() {

        //given
        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        Category category = Category.builder()
                .categoryId(categoryId)
                .build();

        String url ="http://localhost:" + port + "/categorys/" + categoryId;

        ResponseEntity<Category> responseEntity = restTemplate.getForEntity(url, Category.class, category);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 카테고리_등록하기() {

        //given
        UUID categoryId = UUID.randomUUID();
        String categoryName = "카테고리 등록 테스트";
        String stateCode ="CY";


        Category category = Category.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryState(CategoryState.builder().categoryStateCode(stateCode).build())
                .build();

        String url ="http://localhost:" + port + "/categorys";

        //when
        ResponseEntity<Category> responseEntity = restTemplate.postForEntity( url, category, Category.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);


    }


    @Test
    public void 카테고리_수정하기() {

        //given
        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        String categoryName = "카테고리 수정 테스트";
        String stateCode ="CY";

        CategoryDto.RequestUpdate requestUpdate = CategoryDto.RequestUpdate.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .categoryStateCode(stateCode)
                .build();

        String url ="http://localhost:" + port + "/categorys";

        HttpEntity<CategoryDto.RequestUpdate> requestEntity = new HttpEntity<>(requestUpdate);

        //when
        ResponseEntity<Category> responseEntity=restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Category.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    public void 카테고리_삭제하기() {

        String uuid = "fb549f3f-54ce-4e03-9360-211b98fbe20b".replace("-", "");
        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        CategoryDto.RequestInfo requestInfo = CategoryDto.RequestInfo.builder()
                .categoryId(categoryId)
                .build();


        String deleteUrl ="http://localhost:" + port + "/categorys/" + categoryId;

        HttpEntity<CategoryDto.RequestInfo> requestEntity = new HttpEntity<>(requestInfo);

        ResponseEntity<Category> responseEntity=restTemplate.exchange(deleteUrl, HttpMethod.DELETE, requestEntity, Category.class);//        ResponseEntity<Category> response2Entity = restTemplate.getForEntity(getUrl, Category.class, category);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }
}