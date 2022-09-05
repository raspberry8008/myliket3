package com.myliket.myliket3.domain.dto.request.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import com.myliket.myliket3.domain.entity.todo.TodoState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/*
 *  TodoUpdateDto : 수정할 할일 상세 정보
 *
 *  Long todoNo : 할일 고유번호 (필수값 :TRUE)
 *  UUID categoryId : 카테고리 아이디 (필수값 :TRUE)
 *  String todoTitle : 할일 제목 (필수값 :TRUE)
 *  String todoContent : 할일 내용 (필수값 :TRUE)
 *  LocalDateTime todoDayTime : 할일 일정 및 시간 (필수값 :TRUE)
 *  String todoStateCode : 할일 상태코드 (필수값 :TRUE)
 */
@Getter
@NoArgsConstructor
public class TodoUpdateDto {

    @NotNull(message = "할일 고유번호를 입력해주세요.")
    @JsonFormat(pattern = "^[0-9]")
    private Long todoNo;

    @NotNull(message = "카테고리 아이디를 입력해주세요.")
    @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
    private UUID categoryId;

    @NotBlank(message = "할일 제목을 1~15자리로 입력해주세요.")
    @Size(min = 1, max = 15, message = "할일 제목을 1~15자내로 입력해주세요.")
    private String todoTitle; // 할일 제목

    @NotBlank
    @Size(min = 1, max = 100, message = "할일 내용을 1~100사이로 입력해주세요.")
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
    @Pattern(regexp = "[A-Z]{2}$", message = "할일 상태코드(2자리)를 입력해주세요.")
    private String todoStateCode;


    public TodoDetail toEntity() {
        return TodoDetail.builder()
                .todoNo(todoNo)
                .category(Category.builder().categoryId(categoryId).build())
                .todoTitle(todoTitle)
                .todoContent(todoContent)
                .todoDay(getTodoDayTime().toLocalDate())
                .todoTime(getTodoDayTime().toLocalTime())
                .todoState(TodoState.builder().todoStateCode(todoStateCode).build())
                .build();
    }

}
