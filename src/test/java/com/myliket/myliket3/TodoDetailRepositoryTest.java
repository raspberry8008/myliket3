package com.myliket.myliket3;

import com.myliket.myliket3.domain.TodoDetail;
import com.myliket.myliket3.domain.TodoDetailRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @DisplayName("할일 등록 테스트")
    @Test
    public void todoDetailSaveTest() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        TodoDetail todoDetail = TodoDetail.builder()
                .categoryId(testUUID)
                .todoTitle("할일 등록 테스트")
                .todoContent("할일 내용 입니다.")
                .todoDay(LocalDate.parse("2022-09-20"))
                .todoTime(LocalTime.parse("22:00:00"))
                .todoState("TY")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        todoDetailRepository.save(todoDetail);
    }

    @DisplayName("할일 전체 목록 조회 테스트")
    @Test
    public void todoDetailFindAllTest() {

        List<TodoDetail> todoDetails = todoDetailRepository.findAll();

    }

    @DisplayName("할일 단일 조회 테스트")
    @Test
    public void todoDetailFindIdTest() {

        Optional<TodoDetail> todoDetail = todoDetailRepository.findById(1L);

        todoDetail.ifPresent(selectTodoDetail-> {
            System.out.println("todoDetail : " + selectTodoDetail);
            System.out.println("todoDetail : " + selectTodoDetail.getTodoNo());
        });

        Assert.assertTrue(todoDetail.isPresent());

    }

    @DisplayName("할일 수정 테스트")
    @Test
    public void todoDetailUpdateTest() {

        String uuid = "88e0977f-c057-49d1-b4c2-15fa5d261e25".replace("-", "");
        UUID testUUID = new UUID(
                new BigInteger(uuid.substring(0, 16), 16).longValue(),
                new BigInteger(uuid.substring(16), 16).longValue());

        Optional<TodoDetail> todoDetail = todoDetailRepository.findById(1L);
        Assert.assertTrue(todoDetail.isPresent());

        TodoDetail todoDetailInfo = TodoDetail.builder()
                .todoNo(1L)
                .categoryId(testUUID)
                .todoTitle("할일 수정 테스트")
                .todoContent("할일 내용 입니다.")
                .todoDay(LocalDate.parse("2022-09-20"))
                .todoTime(LocalTime.parse("22:00:00"))
                .todoState("TY")
                .createdAt(todoDetail.get().getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();

        todoDetailRepository.save(todoDetailInfo);
    }

    @DisplayName("할일 삭제 테스트")
    @Test
    public void todoDetailDeleteTest() {

        // 1. 할일 조회
        Optional<TodoDetail> todoDetail = todoDetailRepository.findById(6L);

        Assert.assertTrue(todoDetail.isPresent());

        // 2. 할일 삭제
        todoDetailRepository.delete(todoDetail.get());

    }
}
