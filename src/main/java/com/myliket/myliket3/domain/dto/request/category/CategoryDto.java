package com.myliket.myliket3.domain.dto.request.category;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myliket.myliket3.domain.entity.BaseTimeEntity;
import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.category.CategoryState;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class CategoryDto {

    /*
     *  CategoryDto.RequestInfo : 요청한 카테고리 정보
     *
     *  String categoryId : 카테고리 아이디 (기본값 : UUID)
     *
     */
    @Getter
    @NoArgsConstructor
    @Builder
    public static class RequestInfo {

        @NotNull(message = "카테고리 아이디를 입력해주세요.")
        @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        public RequestInfo(UUID categoryId) {
            this.categoryId = categoryId;
        }

        public Category toEntity() {
            return Category.builder()
                    .categoryId(categoryId)
                    .build();
        }
    }


    /*
     *  CategoryDto.RequestInsert : 등록할 카테고리 정보
     *
     *  String categoryId : 카테고리 아이디 (기본값 : UUID)
     *  String categoryName : 카테고리 이름 (필수값 :TRUE)
     *
     */
    @Getter
    @NoArgsConstructor
    public static class RequestInsert extends BaseTimeEntity {

        private UUID categoryId;

        {
            categoryId = UUID.randomUUID();
        }

        @NotBlank(message = "카테고리 이름을 입력해주세요.")
        @Size(min = 1, max = 15, message = "카테고리 이름을 1~15자리로 입력해주세요.")
        private String categoryName; //카테고리 이름

        @NotBlank
        @Pattern(regexp = "[A-Z]{2}$", message = "카테고리 상태코드(2자리)를 입력해주세요.")
        private String categoryStateCode;

        {
            categoryStateCode = "CY";
        }

        public Category toEntity() {
            return Category.builder()
                    .categoryId(categoryId)
                    .categoryName(categoryName)
                    .categoryState(CategoryState.builder().categoryStateCode(categoryStateCode).build())
                    .build();

        }
    }

    /*
     *  CategoryDto.RequestUpdate : 수정할 카테고리 정보
     *
     *  String categoryId : 카테고리 아이디/UUID (필수값 :TRUE)
     *  String categoryName : 카테고리 이름 (필수값 :TRUE)
     *  String categoryState : 카테고리 상태 코드 (필수값 :TRUE)
     */
    @Getter
    @NoArgsConstructor
    @ToString
    public static class RequestUpdate {

        @NotNull(message = "카테고리 아이디를 입력해주세요.")
        @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @NotBlank(message = "카테고리 이름을 입력해주세요.")
        @Size(min = 1, max = 15, message = "카테고리 이름을 1~15자리로 입력해주세요.")
        private String categoryName;

        @NotBlank
        @Pattern(regexp = "[A-Z]{2}$", message = "카테고리 상태코드(2자리)를 입력해주세요.")
        private String categoryStateCode;

        @Builder
        public RequestUpdate(UUID categoryId, String categoryName, String categoryStateCode) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryStateCode = categoryStateCode;
        }

        public Category toEntity() {
            return Category.builder()
                    .categoryId(categoryId)
                    .categoryName(categoryName)
                    .categoryState(CategoryState.builder().categoryStateCode(categoryStateCode).build())
                    .build();
        }
    } // end RequestUpdate

}
