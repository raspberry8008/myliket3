package com.myliket.myliket3.service.todo;

import com.myliket.myliket3.domain.dto.request.todo.TodoPathDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoSaveDto;
import com.myliket.myliket3.domain.dto.request.todo.TodoUpdateDto;
import com.myliket.myliket3.domain.dto.response.common.Response;

public interface TodoService {

    /**
     * 전체 카테고리의 할일을 조회 한다.
     * methodName : allCategoryTodoList
     *
     * @return Response<List < TodoListDto>> 할일 전체 목록
     */
    Response allTodoList() throws Exception;

    /**
     * 카테고리 {categoryId} 에 대한 전체 할일을 조회
     * methodName : getCategoryTodoList
     *
     * @return Response<List < TodoListDto>> 카테고리 {categoryId}의 할일 전체 목록
     */
    Response getCategoryTodoList(TodoPathDto.PathCategoryId pathCategoryId) throws Exception;

    /**
     * 할일 상세조회
     * methodName : getTodoDetail
     *
     * @param pathTodoNo 요청한 할일의 고유번호
     * @return Response(Object) 할일 상세정보
     */
    Response getTodoDetail(TodoPathDto.PathTodoNo pathTodoNo) throws Exception;

    /**
     * 할일 등록
     * methodName : insertTodo
     *
     * @param todoSaveDto(Object) 등록할 할일정보
     */
    void insertTodo(TodoSaveDto todoSaveDto) throws Exception;

    /**
     * 할일 수정
     * methodName : updateTodo
     *
     * @param todoUpdateDto(Object) 수정할 할일정보
     */
    void updateTodo(TodoUpdateDto todoUpdateDto) throws Exception;

    /**
     * 할일 삭제
     * methodName : deleteTodo
     *
     * @param pathTodoNo 삭제요청한 할일의 고유번호
     * @return int 할일 삭제 데이터 처리 수
     */
    void deleteTodo(TodoPathDto.PathTodoNo pathTodoNo) throws Exception;
}
