package com.myliket.myliket3.domain.dto.response.todo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/*
 *  TodoDetailDto : 할일 상세 정보
 *
 *  Long todoNo : 할일 고유번호
 *  UUID categoryId : 카테고리 아이디
 *  String categoryName : 카테고리 이름
 *  String todoTitle : 할일 제목
 *  String todoContent : 할일 내용
 *  LocalDate todoDay : 할일 예정일자
 *  LocalTime todoTime : 할일 예정시간
 *  String todoStateCode : 할일 상태코드
 *  String todoStateKor : 할일 상태코드 한글명
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class TodoDetailDto {

    private Long todoNo;
    private UUID categoryId;
    private String categoryName;
    private String todoTitle;
    private String todoContent;
    private LocalDate todoDay;
    private LocalTime todoTime;
    private String todoStateCode;
    private String todoStateKor;


    public TodoDetailDto(TodoDetail entity) {
        this.todoNo = entity.getTodoNo();
        this.categoryId = entity.getCategory().getCategoryId();
        this.categoryName = entity.getCategory().getCategoryName();
        this.todoTitle = entity.getTodoTitle();
        this.todoContent = entity.getTodoContent();
        this.todoDay = entity.getTodoDay();
        this.todoTime = entity.getTodoTime();
        this.todoStateCode = entity.getTodoState().getTodoStateCode();
        this.todoStateKor = entity.getTodoState().getTodoStateKor();
    }

}
