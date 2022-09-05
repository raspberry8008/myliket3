package com.myliket.myliket3.domain.dto.response.todo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/*
 *  TodoListDto : 할일 목록 정보
 *
 *  Long todoNo : 할일 고유번호
 *  String categoryName : 카테고리 이름
 *  String todoTitle : 할일 제목
 *  LocalDate todoDay : 할일 예정일자
 *  LocalTime todoTime : 할일 예정시간
 *  String todoStateKor : 할일 상태코드 한글명
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class TodoListDto {

    private Long todoNo;
    private String categoryName;
    private String todoTitle;
    private LocalDate todoDay;
    private LocalTime todoTime;
    private String todoStateKor;

    @Builder
    public TodoListDto(Long todoNo, String categoryName, String todoTitle, LocalDate todoDay, LocalTime todoTime, String todoStateKor) {
        this.todoNo = todoNo;
        this.categoryName = categoryName;
        this.todoTitle = todoTitle;
        this.todoDay = todoDay;
        this.todoTime = todoTime;
        this.todoStateKor = todoStateKor;
    }


    public TodoListDto toEntity(TodoDetail entity) {
        return TodoListDto.builder()
                .todoNo(entity.getTodoNo())
                .categoryName(entity.getCategory().getCategoryName())
                .todoTitle(entity.getTodoTitle())
                .todoDay(entity.getTodoDay())
                .todoTime(entity.getTodoTime())
                .todoStateKor(entity.getTodoState().getTodoStateKor())
                .build();
    }

}
