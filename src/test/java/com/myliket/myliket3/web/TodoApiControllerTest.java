package com.myliket.myliket3.web;

import com.myliket.myliket3.domain.dto.request.category.CategoryDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoPathDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoSaveDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoUpdateDto;
import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.category.CategoryRepository;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import com.myliket.myliket3.domain.entity.todo.TodoDetailRepository;
import com.myliket.myliket3.domain.entity.todo.TodoState;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoApiControllerTest {


    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoDetailRepository todoDetailRepository;


    @Test
    public void 할일_전체조회하기() {

        String url ="http://localhost:" + port + "/categorys/todos";

        ResponseEntity<TodoDetail> responseEntity = restTemplate.getForEntity(url, TodoDetail.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void 할일_단일카테고리_전체목록조회하기() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        TodoDetail todoDetail = TodoDetail.builder()
                .category(Category.builder().categoryId(categoryId).build())
                .build();

        String url ="http://localhost:" + port + "/categorys/" + categoryId + "/todos";

        ResponseEntity<TodoDetail> responseEntity = restTemplate.getForEntity(url, TodoDetail.class, todoDetail );

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void 할일_상세조회하기() {

        Long todoNo = 1L;
        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        TodoDetail todoDetail = TodoDetail.builder()
                .category(Category.builder().categoryId(categoryId).build())
                .todoNo(todoNo)
                .build();

        String url ="http://localhost:" + port + "/categorys/" + categoryId + "/todos/" + todoNo ;

        ResponseEntity<TodoDetail> responseEntity = restTemplate.getForEntity(url, TodoDetail.class, todoDetail );

        TodoDetail todoDetail1 = todoDetailRepository.getTodoDetailByCategory_CategoryIdAndTodoNo(todoDetail.getCategory().getCategoryId(), todoDetail.getTodoNo());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

    }

    @Test
    public void 할일_등록하기() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");

        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        String daytime = "2022-10-31T15:12:09";
        LocalDateTime todoDayTime = LocalDateTime.parse(daytime);

        String todoStateCode = "TR";

        TodoSaveDto todoSaveDto = TodoSaveDto.builder()
                .categoryId(categoryId)
                .todoTitle("할일 등록 테스트")
                .todoContent("할일 내용 입니다.")
                .todoDayTime(todoDayTime)
                .todoDay(todoDayTime.toLocalDate())
                .todoTime(todoDayTime.toLocalTime())
                .todoStateCode(todoStateCode)
                .build();

        String url ="http://localhost:" + port + "/categorys/" + categoryId + "/todos";

        ResponseEntity<TodoDetail> responseEntity = restTemplate.postForEntity(url, todoSaveDto, TodoDetail.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    public void 할일_수정하기() {

        Long todoNo = 28L;

        String uuid = "57e28d94-0373-4077-9daf-421c2c493790".replace("-", "");

        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        String daytime = "2022-10-31T15:12:09";
        LocalDateTime todoDayTime = LocalDateTime.parse(daytime);

        String todoStateCode = "TR";

        String todoTitle = "할일 수정 제목 테스트1";

        TodoUpdateDto todoUpdateDto = TodoUpdateDto.builder()
                .todoNo(todoNo)
                .categoryId(categoryId)
                .todoTitle(todoTitle)
                .todoContent("할일 수정 내용 입니다.")
                .todoDayTime(todoDayTime)
                .todoDay(todoDayTime.toLocalDate())
                .todoTime(todoDayTime.toLocalTime())
                .todoStateCode(todoStateCode)
                .build();


        String url ="http://localhost:" + port + "/categorys/" + categoryId + "/todos/" + todoNo;

        HttpEntity<TodoUpdateDto> requestEntity = new HttpEntity<>(todoUpdateDto);

        ResponseEntity<Category> responseEntity=restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Category.class);//        ResponseEntity<Category> response2Entity = restTemplate.getForEntity(getUrl, Category.class, category);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

    }

    @Test
    public void 할일_삭제하기() {

        Long todoNo = 46L;

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");

        UUID categoryId = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        TodoPathDto.PathTodoNo pathTodoNo = TodoPathDto.PathTodoNo.builder()
                .categoryId(categoryId)
                .todoNo(todoNo)
                .build();

        String url ="http://localhost:" + port + "/categorys/" + categoryId + "/todos/" + todoNo;

        HttpEntity<TodoPathDto.PathTodoNo> requestEntity = new HttpEntity<>(pathTodoNo);

        ResponseEntity<Category> responseEntity=restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Category.class);//        ResponseEntity<Category> response2Entity = restTemplate.getForEntity(getUrl, Category.class, category);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}