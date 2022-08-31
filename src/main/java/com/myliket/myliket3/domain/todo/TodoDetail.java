package com.myliket.myliket3.domain.todo;

import com.myliket.myliket3.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

/*
    TodoDetail : 할일 상세정보

    Long todoNo	: 할일 고유번호
    UUID categoryId	: 카테고리 아이디
    String todoTitle :	할일 제목
    Long todoContent : 할일 내용
    LocalDate todoDay : 할일 일정일자
    LocalTime todoTime : 할일 일정시간
    String todoState : 할일 일정상태
    LocalDateTime todoCreatedAt : 할일 최초 등록일시
    LocalDateTime todoUpdatedAt : 할일 마지막 수정일

 */
@Getter
@NoArgsConstructor
@Entity
@Table(name ="tododetail")
public class TodoDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todono")
    private Long todoNo;

    @NotNull
    @Column(name = "categoryid", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @NotBlank
    @Size(max=15)
    @Column(name = "todotitle")
    private String todoTitle;

    @NotBlank
    @Size(max=100)
    @Column(name = "todocontent")
    private String todoContent;

    @NotNull
    @Column(name = "tododay")
    private LocalDate todoDay;
    @NotNull
    @Column(name = "todotime")
    private LocalTime todoTime;

    @NotBlank
    @Size(min=2, max=2)
    @Column(name = "todostate")
    private String todoState;


    @Builder
    public TodoDetail(Long todoNo, UUID categoryId, String todoTitle, String todoContent, LocalDate todoDay, LocalTime todoTime, String todoState) {
        this.todoNo = todoNo;
        this.categoryId = categoryId;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoDay = todoDay;
        this.todoTime = todoTime;
        this.todoState = todoState;
    }


}
