package com.myliket.myliket3.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myliket.myliket3.domain.BaseTimeEntity;
import com.myliket.myliket3.domain.todo.TodoDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class TodoDto {

    /*
     *  RequestInfo : 카테고리 조회 요청 정보
     *
     *  String categoryId : 카테고리 아이디
     *  String todoNo : 할일 고유번호
     *
     */

    @Getter
    @NoArgsConstructor
    public static class PathCategoryId {

        @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern= "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @Builder
        public PathCategoryId(UUID categoryId) {
            this.categoryId = categoryId;
        }

        public TodoDetail toEntity() {
            return TodoDetail.builder()
                    .categoryId(categoryId)
                    .build();
        }
    }

    /*
     *  PathTodoNo : 할일 조회 요청 정보
     *
     *  String categoryId : 카테고리 아이디
     *  String todoNo : 할일 고유번호
     *
     */

    @Getter
    @NoArgsConstructor
    public static class PathTodoNo {

        @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern= "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @JsonFormat(pattern = "^[0-9]")
        private Long todoNo;

        @Builder
        public PathTodoNo(UUID categoryId, Long todoNo) {
            this.categoryId = categoryId;
            this.todoNo = todoNo;
        }

        public TodoDetail toEntity() {
            return TodoDetail.builder()
                    .categoryId(categoryId)
                    .todoNo(todoNo)
                    .build();
        }
    }

    /*
     *  RequestUpdate : 등록할 할일 상세 정보
     *
     *  UUID categoryId : 카테고리 아이디 (필수값 :TRUE)
     *  String todoTitle : 할일 제목 (필수값 :TRUE)
     *  String todoContent : 할일 내용 (필수값 :TRUE)
     *  LocalDateTime todoDayTime : 할일 일정 및 시간 (필수값 :TRUE)
     *
     */

    @Getter
    @NoArgsConstructor
    public static class RequestUpdate extends BaseTimeEntity {

        @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern= "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        private Long todoNo;

        @NotBlank
        @Size(min = 1, max = 15)
        private String todoTitle; // 할일 제목

        @NotBlank
        @Size(min = 1, max = 100)
        private String todoContent; // 할일 내용

        @NotNull(message = "일정을 입력해주세요.")
        @FutureOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime todoDayTime; // 할일 일자 및 시간

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate todoDay;

        @JsonFormat(pattern = "HH:mm:ss")
        private LocalTime todoTime;
        @NotBlank
        @Size(min=2, max=2)
        private String todoState;

        @Builder
        public RequestUpdate(UUID categoryId, Long todoNo, String todoTitle, String todoContent, LocalDateTime todoDayTime, LocalDate todoDay, LocalTime todoTime, String todoState) {
            this.todoNo=todoNo;
            this.categoryId = categoryId;
            this.todoTitle = todoTitle;
            this.todoContent = todoContent;
            this.todoDayTime = todoDayTime;
            this.todoDay = todoDay;
            this.todoTime = todoTime;
            this.todoState =todoState;
        }


        public TodoDetail toEntity(){
            return TodoDetail.builder()
                    .categoryId(categoryId)
                    .todoNo(todoNo)
                    .todoTitle(todoTitle)
                    .todoContent(todoContent)
                    .todoDay(getTodoDayTime().toLocalDate())
                    .todoTime(getTodoDayTime().toLocalTime())
                    .todoState(todoState)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    public static class RequestInsert extends BaseTimeEntity {

        @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern= "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @NotBlank
        @Size(min = 1, max = 15)
        private String todoTitle; // 할일 제목

        @NotBlank
        @Size(min = 1, max = 100)
        private String todoContent; // 할일 내용

        @NotNull(message = "일정을 입력해주세요.")
        @FutureOrPresent
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime todoDayTime; // 할일 일자 및 시간

        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate todoDay;

        @JsonFormat(pattern = "HH:mm:ss")
        private LocalTime todoTime;

        private String todoState;
        {
            todoState="TR";
        }


        @Builder
        public RequestInsert(UUID categoryId,String todoTitle, String todoContent, LocalDateTime todoDayTime, LocalDate todoDay, LocalTime todoTime, String todoState) {
            this.categoryId = categoryId;
            this.todoTitle = todoTitle;
            this.todoContent = todoContent;
            this.todoDayTime = todoDayTime;
            this.todoDay = todoDay;
            this.todoTime = todoTime;
            this.todoState =todoState;
        }


        public TodoDetail toEntity(){
            return TodoDetail.builder()
                    .categoryId(categoryId)
                    .todoTitle(todoTitle)
                    .todoContent(todoContent)
                    .todoDay(getTodoDayTime().toLocalDate())
                    .todoTime(getTodoDayTime().toLocalTime())
                    .todoState(todoState)
                    .build();
        }
    }


}
