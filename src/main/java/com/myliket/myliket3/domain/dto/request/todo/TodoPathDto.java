package com.myliket.myliket3.domain.dto.request.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myliket.myliket3.domain.entity.category.Category;
import com.myliket.myliket3.domain.entity.todo.TodoDetail;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

public class TodoPathDto {

    /*
     *  PathCategoryId : 요청한 카테고리 아이디
     *
     *  UUID categoryId : 카테고리 아이디
     *
     */

    @Getter
    public static class PathCategoryId {

        @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @Builder
        public PathCategoryId(UUID categoryId) {
            this.categoryId = categoryId;
        }

        public Category toEntity() {
            return Category.builder()
                    .categoryId(categoryId)
                    .build();
        }
    }

    /*
     *  PathTodoNo : 요청한 카테고리 아이디, 할일 고유번호
     *
     *  UUID categoryId : 카테고리 아이디
     *  Long todoNo : 할일 고유번호
     *
     */

    @Getter
    public static class PathTodoNo {

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.OBJECT, pattern = "^[0-9a-f]{8}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{4}-?[0-9a-f]{12}$")
        private UUID categoryId;

        @NotNull
        @JsonFormat(pattern = "^[0-9]")
        private Long todoNo;

        @Builder
        public PathTodoNo(UUID categoryId, Long todoNo) {
            this.categoryId = categoryId;
            this.todoNo = todoNo;
        }

        public TodoDetail toEntity() {
            return TodoDetail.builder()
                    .category(Category.builder().categoryId(categoryId).build())
                    .todoNo(todoNo)
                    .build();
        }
    }

}
