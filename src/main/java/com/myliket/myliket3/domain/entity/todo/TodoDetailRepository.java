package com.myliket.myliket3.domain.entity.todo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoDetailRepository extends JpaRepository<TodoDetail, Long> {

    /**
     * 할일 전체 목록 조회(TodoDetail)
     * methodName : getTodoDetailByCategory_CategoryIdAndTodoNo
     *
     * @return TodoDetail(Object)
     */
    List<TodoDetail> findAllByOrderByCategoryCreatedAtAscTodoNoAsc();

    /**
     * 카테고리 아이디의 할일목록 조회
     * methodName : findByCategory_CategoryIdOrderByTodoNoAsc
     *
     * @param categoryId 카테고리 아이디
     * @return List<TodoDetail>
     */
    List<TodoDetail> findByCategory_CategoryIdOrderByTodoNoAsc(UUID categoryId);

    /**
     * 할일 상세 조회(Optional<TodoDetail>)
     * methodName : findByCategory_CategoryIdAndTodoNo
     *
     * @param categoryId 카테고리 아이디, todoNo 할일 고유번호
     * @return Optional<TodoDetail>
     */
    Optional<TodoDetail> findByCategory_CategoryIdAndTodoNo(UUID categoryId, Long todoNo);

    /**
     * 할일 상세 조회(TodoDetail)
     * methodName : getTodoDetailByCategory_CategoryIdAndTodoNo
     *
     * @param categoryId 카테고리 아이디, todoNo 할일 고유번호
     * @return TodoDetail(Object)
     */
    TodoDetail getTodoDetailByCategory_CategoryIdAndTodoNo(UUID categoryId, Long todoNo);


}
