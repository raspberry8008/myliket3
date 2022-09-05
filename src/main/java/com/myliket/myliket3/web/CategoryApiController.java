package com.myliket.myliket3.web;

import com.myliket.myliket3.domain.dto.request.category.CategoryDto;
import com.myliket.myliket3.domain.dto.response.common.Response;
import com.myliket.myliket3.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/categorys")
public class CategoryApiController {

    private final CategoryService categoryService;

    /**
     * 카테고리 전체 목록 조회 API
     *
     * @return ResponseEntity<Response> 200 OK, 카테고리 정보 목록
     */
    @GetMapping(value = "")
    public ResponseEntity<?> allCategoryList() throws Exception {
        return ResponseEntity.ok().body(categoryService.allCategoryList());
    }

    /**
     * 카테고리 상세조회 API
     *
     * @param categoryId 상세조회 할 카테고리 아이디
     * @return ResponseEntity<Response> 200 OK, 카테고리 상세정보
     */

    @GetMapping(value = "/{categoryId}")
    public ResponseEntity<Response> getCategoryDetail(@PathVariable("categoryId") @NotBlank UUID categoryId) throws Exception {
        return ResponseEntity.ok().body(categoryService.getCategoryDetail(CategoryDto.RequestInfo.builder().categoryId(categoryId).build()));
    }

    /**
     * 카테고리 등록 API
     *
     * @param requestInsert(Object) 등록할 카테고리 정보
     * @return ResponseEntity<Object> 201 Created
     */
    @PostMapping(value = "")
    public ResponseEntity<Void> insertCategory(@RequestBody @Validated CategoryDto.RequestInsert requestInsert) throws Exception {
        categoryService.insertCategory(requestInsert);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 카테고리 수정 API
     *
     * @param requestUpdate(Object) 수정할 카테고리 정보
     * @return ResponseEntity<Object> 201 Created
     */
    @PutMapping(value = "")
    public ResponseEntity<Object> updateCategory(@RequestBody @Validated CategoryDto.RequestUpdate requestUpdate) throws Exception {
        categoryService.updateCategory(requestUpdate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 카테고리 삭제 API
     *
     * @param categoryId 삭제할 카테고리 아이디
     * @return ResponseEntity<Object> 201 CREATED : 이동할 페이지 없음
     */
    @DeleteMapping(value = "/{categoryId}")
    public ResponseEntity<Object> deleteCategory(@PathVariable("categoryId") @NotBlank UUID categoryId) throws Exception {
        categoryService.deleteCategory(CategoryDto.RequestInfo.builder().categoryId(categoryId).build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
