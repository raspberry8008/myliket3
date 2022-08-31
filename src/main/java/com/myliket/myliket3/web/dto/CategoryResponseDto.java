package com.myliket.myliket3.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.myliket.myliket3.domain.category.Category;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class CategoryResponseDto {

    private UUID categoryId;
    private String categoryName;
    private String categoryState;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CategoryResponseDto(Category entity) {
        this.categoryId = entity.getCategoryId();
        this.categoryName = entity.getCategoryName();
        this.categoryState = entity.getCategoryState();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
