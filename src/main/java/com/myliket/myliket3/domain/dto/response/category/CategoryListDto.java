package com.myliket.myliket3.domain.dto.response.category;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myliket.myliket3.domain.entity.category.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/*
 *  CategoryListDto : 카테고리 목록 조회 정보
 *
 *  UUID categoryId : 카테고리 아이디/UUID
 *  String categoryName : 카테고리 이름
 *  String categoryStateKor : 카테고리 상태 코드 한글명
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@NoArgsConstructor
public class CategoryListDto {

    private UUID categoryId;
    private String categoryName;
    private String categoryStateKor;

    @Builder
    public CategoryListDto(UUID categoryId, String categoryName, String categoryStateKor) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryStateKor = categoryStateKor;
    }

    public CategoryListDto toEntity(Category entity) {
        return CategoryListDto.builder()
                .categoryId(entity.getCategoryId())
                .categoryName(entity.getCategoryName())
                .categoryStateKor(entity.getCategoryState().getCategoryStateKor())
                .build();

    }

}
