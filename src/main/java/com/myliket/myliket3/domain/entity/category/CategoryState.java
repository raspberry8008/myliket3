package com.myliket.myliket3.domain.entity.category;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 *  CategoryState : 카테고리 상태 정보
 *
 *  String categoryStateCode : 카테고리 상태코드
 *  String categoryStateKor : 카테고리 상태코드 한글명
 */
@Getter
@NoArgsConstructor
@Entity
@Table(name = "categorystate")
public class CategoryState {

    @Id
    @Column(name = "categorystatecode")
    private String categoryStateCode;

    @Column(name = "categorystatekor")
    private String categoryStateKor;

    @Builder
    public CategoryState(String categoryStateCode, String categoryStateKor) {
        this.categoryStateCode = categoryStateCode;
        this.categoryStateKor = categoryStateKor;
    }
}
