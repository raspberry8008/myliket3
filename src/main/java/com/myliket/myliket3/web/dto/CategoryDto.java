package com.myliket.myliket3.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myliket.myliket3.domain.BaseTimeEntity;
import com.myliket.myliket3.domain.category.Category;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

public class CategoryDto {

    /*
     *  CategoryDto.RequestInfo : 조회 요청한 카테고리 정보
     *
     *  String categoryId : 카테고리 아이디 (기본값 : UUID)
     *
     */
    @Getter
    @NoArgsConstructor
    public static class RequestInfo {

        @JsonFormat(shape=JsonFormat.Shape.OBJECT, pattern= "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @Builder
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


        @NotNull
        @Size(max=15)
        private String categoryName; //카테고리 이름

        private String categoryState;
        {
            categoryState="CY";
        }


        @Builder
        public RequestInsert(UUID categoryId, String categoryName, String categoryState) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryState = categoryState;
        }


        public Category toEntity(){
            return Category.builder()
                    .categoryId(categoryId)
                    .categoryName(categoryName)
                    .categoryState(categoryState)
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
    @Setter
    @ToString
    public static class RequestUpdate {

        @JsonFormat(shape=JsonFormat.Shape.OBJECT)
//        @Pattern(regexp = "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$", message = "카테고리 아이디를 입력해주세요.")
        private UUID categoryId;

        @NotBlank
        @Size(min=1, max=15)
        private String categoryName;
        @NotBlank
        @Pattern(regexp = "[A-Z]{2}$", message = "카테고리 상태코드를 입력해주세요")
        private String categoryState;




        @Builder
        public RequestUpdate(UUID categoryId, String categoryName, String categoryState) {
            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.categoryState = categoryState;
        }

        public Category toEntity(){
            return Category.builder()
                    .categoryId(categoryId)
                    .categoryName(categoryName)
                    .categoryState(categoryState)
                    .build();
        }

    }
}
