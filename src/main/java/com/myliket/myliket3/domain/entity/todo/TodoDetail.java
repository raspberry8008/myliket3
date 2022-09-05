package com.myliket.myliket3.domain.entity.todo;


import com.myliket.myliket3.domain.entity.BaseTimeEntity;
import com.myliket.myliket3.domain.entity.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;


/*
    TodoDetail : 할일 상세정보

    Long todoNo	: 할일 고유번호
    UUID categoryId	: 카테고리 아이디 (Category category Join)
    String todoTitle :	할일 제목
    String todoContent : 할일 내용
    LocalDate todoDay : 할일 일정일자
    LocalTime todoTime : 할일 일정시간
    String todoStateCode : 할일 일정상태 (TodoState todoState Join)

 */

@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tododetail")
public class TodoDetail extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todono")
    private Long todoNo;

    @NotBlank
    @Size(max = 15)
    @Column(name = "todotitle")
    private String todoTitle;

    @NotBlank
    @Size(max = 100)
    @Column(name = "todocontent")
    private String todoContent;

    @NotNull
    @Column(name = "tododay")
    private LocalDate todoDay;
    @NotNull
    @Column(name = "todotime")
    private LocalTime todoTime;

    @Builder
    public TodoDetail(Category category, TodoState todoState, Long todoNo, String todoTitle, String todoContent, LocalDate todoDay, LocalTime todoTime) {
        this.category = category;
        this.todoState = todoState;
        this.todoNo = todoNo;
        this.todoTitle = todoTitle;
        this.todoContent = todoContent;
        this.todoDay = todoDay;
        this.todoTime = todoTime;
    }

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "todostatecode")
    private TodoState todoState;


}
