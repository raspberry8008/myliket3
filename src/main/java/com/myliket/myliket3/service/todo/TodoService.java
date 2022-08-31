package com.myliket.myliket3.service.todo;

import com.myliket.myliket3.web.dto.Response;
import com.myliket.myliket3.web.dto.TodoDto;

public interface TodoService {

    /**
     * 전체 카테고리의 할일을 조회 한다.
     * methodName : allCategoryTodoList
     * @return List<?> 할일 목록
     * */
    Response allTodoList () throws Exception;

    /**
     * 카테고리 {categoryId} 에 대한 전체 할일을 조회
     * methodName : getCategoryTodoList
     * @return List<?> 할일 목록
     * */
    Response getCategoryTodoList (TodoDto.PathCategoryId pathCategoryId) throws Exception;

    /**
     * 할일 상세조회
     * methodName : getTodoDetail
     *
     * @param pathTodoNo 요청한 할일의 고유번호
     * @return todoVO(Object) 할일 상세정보
     */
    Response getTodoDetail (TodoDto.PathTodoNo pathTodoNo) throws Exception;

    /**
     * 할일 등록
     * methodName : insertTodo
     *
     * @param requestInsert(Object) 등록할 할일정보
     */
    void insertTodo (TodoDto.RequestInsert requestInsert) throws Exception;

    /**
     * 할일 수정
     * methodName : updateTodo
     * @param requestUpdate(Object) 수정할 할일정보
     * */
    void updateTodo(TodoDto.RequestUpdate requestUpdate) throws Exception;

    /**
     * 할일 삭제
     * methodName : deleteTodo
     * @param pathTodoNo 삭제요청한 할일의 고유번호
     * @return int 할일 삭제 데이터 처리 수
     * */
    void deleteTodo (TodoDto.PathTodoNo pathTodoNo) throws Exception;
}
