package com.myliket.myliket3.domain.entity.todo;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 *  TodoState : 할일 상태 정보
 *
 *  String todoStateCode : 할일 상태코드
 *  String todoStateKor : 할일 상태코드 한글명
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "todostate")
public class TodoState {

    @Id
    @GeneratedValue
    @Column(name = "todostatecode" , updatable = false)
    private String todoStateCode;

    @Column(name = "todostatekor", updatable = false)
    private String todoStateKor;


    @Builder
    public TodoState(String todoStateCode, String todoStateKor) {
        this.todoStateCode = todoStateCode;
        this.todoStateKor = todoStateKor;
    }
}
