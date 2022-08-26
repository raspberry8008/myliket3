package com.myliket.myliket3.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
public class TodoDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoNo;

    @Column(name = "categoryId", columnDefinition = "BINARY(16)")
    private UUID categoryId;

    @Column(name = "todoTitle")
    private String todoTitle;

    @Column(name = "todoContent")
    private String todoContent;

    @Column(name = "todoDay")
    private LocalDate todoDay;

    @Column(name = "todoTime")
    private LocalTime todoTime;

    @Column(name = "todoState")
    private String todoState;

    @Column(name = "createdAt")
    private LocalDateTime todoCreatedAt;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;
}
