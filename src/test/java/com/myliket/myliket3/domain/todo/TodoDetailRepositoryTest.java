package com.myliket.myliket3.domain.todo;

import com.myliket.myliket3.domain.dto.request.category.CategoryDto;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import com.myliket.myliket3.domain.entity.todo.TodoDetailRepository;
import com.myliket.myliket3.domain.entity.todo.TodoDetailRepositorySupport;
import com.myliket.myliket3.domain.entity.todo.TodoState;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoDetailRepositoryTest {

    @Autowired
    TodoDetailRepository todoDetailRepository;

    @Autowired
    TodoDetailRepositorySupport todoDetailRepositorySupport;

    @Test
    public void 할일_등록하기() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        TodoDetail todoDetail = TodoDetail.builder()
                .category(CategoryDto.RequestInfo.builder().categoryId(testUUID).build().toEntity())
                .todoTitle("할일 등록 테스트")
                .todoContent("할일 내용 입니다.")
                .todoDay(LocalDate.parse("2022-09-20"))
                .todoTime(LocalTime.parse("22:00:00"))
                .todoState(TodoState.builder().todoStateCode("TR").build())
                .build();


        todoDetailRepository.save(todoDetail);
    }

    @Test
    public void 할일_전체목록조회() {

        List<TodoDetail> todoDetails = todoDetailRepository.findAllByOrderByCategoryCreatedAtAscTodoNoAsc();
//        List<?> todoDetails= todoDetailRepositorySupport.findAll();

    }

    @Test
    public void 할일_단일카테고리_전체목록조회() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        List<TodoDetail> todoDetails = todoDetailRepository.findByCategory_CategoryIdOrderByTodoNoAsc(testUUID);

    }

    @Test
    public void 할일_상세조회() {

        Optional<TodoDetail> todoDetail = todoDetailRepository.findById(1L);

        todoDetail.ifPresent(selectTodoDetail-> {
            System.out.println("todoDetail : " + selectTodoDetail);
            System.out.println("todoDetail : " + selectTodoDetail.getTodoNo());
        });

        Assert.assertTrue(todoDetail.isPresent());

    }

    @Test
    public void 할일_수정하기() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        Optional<TodoDetail> todoDetail = todoDetailRepository.findById(1L);
        Assert.assertTrue(todoDetail.isPresent());

        TodoDetail todoDetailInfo = TodoDetail.builder()
                .todoNo(1L)
                .category(CategoryDto.RequestInfo.builder().categoryId(testUUID).build().toEntity())
                .todoTitle("할일 수정 테스트")
                .todoContent("할일 내용 입니다.")
                .todoDay(LocalDate.parse("2022-09-20"))
                .todoTime(LocalTime.parse("22:00:00"))
                .todoState(TodoState.builder().todoStateCode("TR").build())
                .build();

        todoDetailRepository.save(todoDetailInfo);
    }

    @Test
    public void 할일_삭제하기() {

        // 1. 할일 조회
        Optional<TodoDetail> todoDetail = todoDetailRepository.findById(6L);

        Assert.assertTrue(todoDetail.isPresent());

        // 2. 할일 삭제
        todoDetailRepository.delete(todoDetail.get());

    }
}
