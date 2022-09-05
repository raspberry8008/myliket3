package com.myliket.myliket3.domain.dto.response.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myliket.myliket3.domain.entity.category.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
 *  CategoryDetailDto : 카테고리 상세 조회 정보
 *
 *  UUID categoryId : 카테고리 아이디/UUID
 *  String categoryName : 카테고리 이름
 *  String categoryStateCode : 카테고리 상태 코드
 *  String categoryStateKor : 카테고리 상태 코드 한글명
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class CategoryDetailDto {

    private UUID categoryId;
    private String categoryName;
    private String categoryStateCode;
    private String categoryStateKor;

    public CategoryDetailDto(Category entity) {
        this.categoryId = entity.getCategoryId();
        this.categoryName = entity.getCategoryName();
        this.categoryStateCode = entity.getCategoryState().getCategoryStateCode();
        this.categoryStateKor = entity.getCategoryState().getCategoryStateKor();
    }

}
