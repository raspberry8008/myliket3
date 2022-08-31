package com.myliket.myliket3.service.category;

import com.myliket.myliket3.domain.category.Category;
import com.myliket.myliket3.web.dto.CategoryDto;
import com.myliket.myliket3.web.dto.Response;

public interface CategoryService {

    /**
     * 카테고리 전체 목록 조회
     * methodName : allCategoryList
     * @return List<?> 카테고리 목록
     * */
    Response allCategoryList() throws Exception;

    /**
     * 카테고리 상세조회
     * methodName : getCategoryDetail
     *
     * @param requestInfo 요청한 카테고리 아이디
     * @return categoryVO(Object) 카테고리 상세정보
     */
    Response getCategoryDetail (CategoryDto.RequestInfo requestInfo) throws Exception ;

    /**
     * 카테고리 등록
     * methodName : insertCategory
     *
     * @param requestInsert(Object) 등록할 카테고리 정보
     */
    void insertCategory (CategoryDto.RequestInsert requestInsert) throws Exception;

    /**
     * 카테고리 수정
     * methodName : updateCategory
     * @param requestUpdate 수정할 카테고리 정보
     */
    void updateCategory(CategoryDto.RequestUpdate requestUpdate) throws Exception;

    /**
     * 카테고리 삭제
     * methodName : deleteCategory
     * @param requestInfo 삭제요청한 카테고리 아이디
     * @return int 카테고리 삭제 데이터 처리 수
     * */
    void deleteCategory (CategoryDto.RequestInfo requestInfo) throws Exception;
}
