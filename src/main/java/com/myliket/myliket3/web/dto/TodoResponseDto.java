package com.myliket.myliket3.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myliket.myliket3.domain.todo.TodoDetail;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class TodoResponseDto {

    private Long todoNo;
    private UUID categoryId;
    private String todoTitle;
    private String todoContent;
    private LocalDate todoDay;
    private LocalTime todoTime;
    private String todoState;


    public TodoResponseDto(TodoDetail entity) {
        this.todoNo = entity.getTodoNo();
        this.categoryId = entity.getCategoryId();
        this.todoTitle = entity.getTodoTitle();
        this.todoContent = entity.getTodoContent();
        this.todoDay = entity.getTodoDay();
        this.todoTime = entity.getTodoTime();
        this.todoState = entity.getTodoState();
    }
}
