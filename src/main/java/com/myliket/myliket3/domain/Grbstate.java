package com.myliket.myliket3.domain;

/*
    Grbstate : 공통 상태코드 상세

    stateCode: 공통 상태 코드
    stateKor: 공통 상태코드 한글명
    stateCategory: 공통 상태코드 카테고리
 */

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name ="grbstate")
public class Grbstate {

    @Id
    @Column(name = "stateCode")
    private String stateCode;

    @Column(name = "stateKor")
    private String stateKor;

    @Column(name = "stateCategory")
    private String stateCategory;

}
